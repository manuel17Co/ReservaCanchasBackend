package com.reservas.api.services;

import com.reservas.api.dto.CrearReservaRequest;
import com.reservas.api.dto.ReservaDTO;
import com.reservas.api.models.Horario;
import com.reservas.api.models.Reserva;
import com.reservas.api.models.Usuario;
import com.reservas.api.repositories.HorarioRepository;
import com.reservas.api.repositories.ReservaRepository;
import com.reservas.api.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    private static final Logger logger = LoggerFactory.getLogger(ReservaService.class);

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<ReservaDTO> listarMisReservas() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correo = authentication.getName();
        logger.info("Listando reservas para usuario: {}", correo);

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correo));

        List<Reserva> reservas = reservaRepository.findByUsuarioIdWithDetails(usuario.getId());
        logger.info("Reservas encontradas: {}", reservas.size());

        return reservas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public ReservaDTO obtenerPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));
        return convertirADTO(reserva);
    }

    public ReservaDTO crear(CrearReservaRequest request) {
        logger.info("Intentando crear reserva - Horario ID: {}, Fecha: {}", request.getHorarioId(), request.getFecha());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correo = authentication.getName();
        logger.info("Usuario autenticado: {}", correo);

        // 1. Buscar usuario
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correo));
        logger.info("Usuario encontrado: ID={}", usuario.getId());

        // 2. Buscar horario
        Horario horario = horarioRepository.findById(request.getHorarioId())
                .orElseThrow(() -> new RuntimeException("Horario no encontrado con ID: " + request.getHorarioId()));
        logger.info("Horario encontrado: ID={}, cancha_id={}", horario.getId(), horario.getCancha().getId());

        // 3. Verificar si ya existe reserva para este horario en la misma fecha
        boolean yaReservado = reservaRepository.existsByHorarioIdAndFecha(horario.getId(), request.getFecha());
        logger.info("¿Ya está reservado? {}", yaReservado);

        if (yaReservado) {
            throw new RuntimeException("Este horario ya está reservado para la fecha " + request.getFecha());
        }

        // 4. Crear reserva
        Reserva reserva = new Reserva();
        reserva.setEstado("CONFIRMADA");
        reserva.setTotalPago(horario.getCancha().getPrecioHora());
        reserva.setFecha(request.getFecha());
        reserva.setUsuario(usuario);
        reserva.setHorario(horario);

        logger.info("Guardando reserva: estado={}, totalPago={}, fecha={}, usuarioId={}, horarioId={}",
            reserva.getEstado(), reserva.getTotalPago(), reserva.getFecha(),
            reserva.getUsuario().getId(), reserva.getHorario().getId());

        Reserva guardada = reservaRepository.save(reserva);
        logger.info("Reserva guardada exitosamente con ID: {}", guardada.getId());

        return convertirADTO(guardada);
    }

    public ReservaDTO cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correo = authentication.getName();

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correo));

        if (!reserva.getUsuario().getId().equals(usuario.getId())) {
            throw new RuntimeException("No tienes permiso para cancelar esta reserva");
        }

        reserva.setEstado("CANCELADA");
        Reserva guardada = reservaRepository.save(reserva);

        return convertirADTO(guardada);
    }

    private ReservaDTO convertirADTO(Reserva reserva) {
        Horario horario = reserva.getHorario();
        return new ReservaDTO(
                reserva.getId(),
                reserva.getEstado(),
                reserva.getTotalPago(),
                reserva.getCreatedAt(),
                reserva.getFecha(),
                horario.getHoraInicio(),
                horario.getHoraFin(),
                horario.getCancha().getNombre(),
                horario.getCancha().getTipoCancha().getNombre(),
                horario.getCancha().getSede().getNombre(),
                reserva.getUsuario().getId(),
                reserva.getUsuario().getNombre(),
                reserva.getUsuario().getCorreo()
        );
    }
}
