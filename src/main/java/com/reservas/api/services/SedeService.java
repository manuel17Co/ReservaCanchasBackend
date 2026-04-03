package com.reservas.api.services;

import com.reservas.api.dto.SedeDTO;
import com.reservas.api.models.Sede;
import com.reservas.api.repositories.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SedeService {

    @Autowired
    private SedeRepository sedeRepository;

    public List<SedeDTO> listarTodas() {
        return sedeRepository.findByOrderByNombreAsc().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public SedeDTO obtenerPorId(Long id) {
        Sede sede = sedeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sede no encontrada"));
        return convertirADTO(sede);
    }

    public SedeDTO crear(Sede sede) {
        if (sedeRepository.existsByTelefono(sede.getTelefono())) {
            throw new RuntimeException("Ya existe una sede con ese teléfono");
        }
        Sede guardada = sedeRepository.save(sede);
        return convertirADTO(guardada);
    }

    private SedeDTO convertirADTO(Sede sede) {
        return new SedeDTO(
                sede.getId(),
                sede.getNombre(),
                sede.getDireccion(),
                sede.getTelefono()
        );
    }
}
