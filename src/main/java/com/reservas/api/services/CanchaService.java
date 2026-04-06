package com.reservas.api.services;

import com.reservas.api.dto.CanchaConDisponibilidadDTO;
import com.reservas.api.dto.CanchaDTO;
import com.reservas.api.models.Cancha;
import com.reservas.api.repositories.CanchaRepository;
import com.reservas.api.repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CanchaService {

    @Autowired
    private CanchaRepository canchaRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private HorarioService horarioService;

    public List<CanchaDTO> listarCanchas(Long sedeId, Long tipoCanchaId) {
        List<Cancha> canchas = canchaRepository.findAllWithDetails(sedeId, tipoCanchaId);
        return canchas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<CanchaConDisponibilidadDTO> listarCanchasConFiltros(
            Long sedeId, Long tipoCanchaId, LocalDate fecha) {

        List<Cancha> canchas = canchaRepository.findAllWithDetails(sedeId, tipoCanchaId);

        return canchas.stream()
                .map(cancha -> {
                    boolean disponible = !horarioRepository.findByCanchaIdWithDetails(cancha.getId()).isEmpty()
                            && horarioService.tieneHorariosDisponibles(cancha.getId(), fecha);
                    return convertirAConDisponibilidadDTO(cancha, disponible);
                })
                .collect(Collectors.toList());
    }

    public CanchaDTO obtenerPorId(Long id) {
        Cancha cancha = canchaRepository.findByIdWithDetails(id);
        if (cancha == null) {
            throw new RuntimeException("Cancha no encontrada");
        }
        return convertirADTO(cancha);
    }

    public List<CanchaConDisponibilidadDTO> listarCanchasConDisponibilidad(
            Long sedeId, Long tipoCanchaId, LocalDate fecha) {

        List<Cancha> canchas = canchaRepository.findAllWithDetails(sedeId, tipoCanchaId);

        return canchas.stream()
                .map(cancha -> {
                    boolean tieneDisponibilidad = horarioService.tieneHorariosDisponibles(cancha.getId(), fecha);
                    return convertirAConDisponibilidadDTO(cancha, tieneDisponibilidad);
                })
                .collect(Collectors.toList());
    }

    public CanchaDTO crear(Cancha cancha) {
        Cancha guardada = canchaRepository.save(cancha);
        return convertirADTO(guardada);
    }

    public CanchaDTO actualizar(Long id, Cancha canchaActualizada) {
        Cancha cancha = canchaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        cancha.setNombre(canchaActualizada.getNombre());
        cancha.setDescripcion(canchaActualizada.getDescripcion());
        cancha.setCapacidad(canchaActualizada.getCapacidad());
        cancha.setImagen(canchaActualizada.getImagen());
        cancha.setPrecioHora(canchaActualizada.getPrecioHora());
        cancha.setActiva(canchaActualizada.getActiva());
        cancha.setSede(canchaActualizada.getSede());
        cancha.setTipoCancha(canchaActualizada.getTipoCancha());

        Cancha guardada = canchaRepository.save(cancha);
        return convertirADTO(guardada);
    }

    private CanchaDTO convertirADTO(Cancha cancha) {
        return new CanchaDTO(
                cancha.getId(),
                cancha.getNombre(),
                cancha.getDescripcion(),
                cancha.getCapacidad(),
                cancha.getImagen(),
                cancha.getPrecioHora(),
                cancha.getActiva(),
                cancha.getSede().getId(),
                cancha.getSede().getNombre(),
                cancha.getTipoCancha().getId(),
                cancha.getTipoCancha().getNombre()
        );
    }

    private CanchaConDisponibilidadDTO convertirAConDisponibilidadDTO(Cancha cancha, Boolean tieneDisponibilidad) {
        return new CanchaConDisponibilidadDTO(
                cancha.getId(),
                cancha.getNombre(),
                cancha.getDescripcion(),
                cancha.getPrecioHora(),
                cancha.getSede().getId(),
                cancha.getSede().getNombre(),
                cancha.getTipoCancha().getId(),
                cancha.getTipoCancha().getNombre(),
                tieneDisponibilidad
        );
    }
}
