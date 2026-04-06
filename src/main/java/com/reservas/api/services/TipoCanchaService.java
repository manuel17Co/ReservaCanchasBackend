package com.reservas.api.services;

import com.reservas.api.dto.TipoCanchaDTO;
import com.reservas.api.models.TipoCancha;
import com.reservas.api.repositories.TipoCanchaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoCanchaService {

    @Autowired
    private TipoCanchaRepository tipoCanchaRepository;

    public List<TipoCanchaDTO> listarTodos() {
        return tipoCanchaRepository.findByOrderByNombreAsc().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public TipoCanchaDTO obtenerPorId(Long id) {
        TipoCancha tipo = tipoCanchaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de cancha no encontrado"));
        return convertirADTO(tipo);
    }

    public TipoCanchaDTO crear(TipoCancha tipoCancha) {
        if (tipoCanchaRepository.existsByNombre(tipoCancha.getNombre())) {
            throw new RuntimeException("Ya existe un tipo de cancha con ese nombre");
        }
        TipoCancha guardada = tipoCanchaRepository.save(tipoCancha);
        return convertirADTO(guardada);
    }

    private TipoCanchaDTO convertirADTO(TipoCancha tipo) {
        return new TipoCanchaDTO(
                tipo.getId(),
                tipo.getNombre()
        );
    }
}
