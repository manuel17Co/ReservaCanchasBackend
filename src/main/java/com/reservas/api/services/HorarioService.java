package com.reservas.api.services;

import com.reservas.api.dto.HorarioDisponibleDTO;
import com.reservas.api.dto.HorarioDTO;
import com.reservas.api.models.Horario;
import com.reservas.api.repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HorarioService {

    private static final Logger logger = LoggerFactory.getLogger(HorarioService.class);

    @Autowired
    private HorarioRepository horarioRepository;

    public List<HorarioDTO> listarPorCancha(Long canchaId) {
        List<Horario> horarios = horarioRepository.findByCanchaIdOrderByHoraInicioAsc(canchaId);
        logger.info("Horarios para cancha {}: {}", canchaId, horarios.size());
        return horarios.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<HorarioDisponibleDTO> listarHorariosDisponibles(Long canchaId, LocalDate fecha) {
        logger.info("Buscando horarios disponibles para cancha {} en fecha {}", canchaId, fecha);

        // Primero verificamos todos los horarios de la cancha
        List<Horario> todosHorarios = horarioRepository.findSimpleByCanchaId(canchaId);
        logger.info("Total horarios en DB para cancha {}: {}", canchaId, todosHorarios.size());

        // Luego los disponibles (sin reservas en esa fecha)
        List<Horario> horariosDisponibles = horarioRepository.findHorariosDisponiblesWithDetails(canchaId, fecha);
        logger.info("Horarios disponibles (sin reservas) para cancha {} en {}: {}", canchaId, fecha, horariosDisponibles.size());

        return horariosDisponibles.stream()
                .map(horario -> new HorarioDisponibleDTO(
                        horario.getId(),
                        horario.getHoraInicio(),
                        horario.getHoraFin(),
                        horario.getCancha().getId(),
                        horario.getCancha().getNombre(),
                        horario.getCancha().getPrecioHora(),
                        horario.getCancha().getTipoCancha().getNombre(),
                        horario.getCancha().getSede().getNombre()
                ))
                .collect(Collectors.toList());
    }

    public boolean tieneHorariosDisponibles(Long canchaId, LocalDate fecha) {
        List<Horario> disponibles = horarioRepository.findHorariosDisponibles(canchaId, fecha);
        return !disponibles.isEmpty();
    }

    public HorarioDTO obtenerPorId(Long id) {
        Horario horario = horarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));
        return convertirADTO(horario);
    }

    public HorarioDTO crear(Horario horario) {
        Horario guardado = horarioRepository.save(horario);
        return convertirADTO(guardado);
    }

    private HorarioDTO convertirADTO(Horario horario) {
        return new HorarioDTO(
                horario.getId(),
                horario.getHoraInicio(),
                horario.getHoraFin(),
                horario.getDisponible(),
                horario.getCancha().getId(),
                horario.getCancha().getNombre()
        );
    }
}
