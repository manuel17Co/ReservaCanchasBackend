package com.reservas.api.controllers;

import com.reservas.api.dto.CanchaConDisponibilidadDTO;
import com.reservas.api.dto.CanchaDTO;
import com.reservas.api.models.Cancha;
import com.reservas.api.services.CanchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/canchas")
@CrossOrigin(origins = "*")
public class CanchaController {

    @Autowired
    private CanchaService canchaService;

    @GetMapping
    public ResponseEntity<List<?>> listarCanchas(
            @RequestParam(required = false) Long sedeId,
            @RequestParam(required = false) Long tipoCanchaId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        if (fecha != null) {
            List<CanchaConDisponibilidadDTO> canchas = canchaService.listarCanchasConFiltros(sedeId, tipoCanchaId, fecha);
            return ResponseEntity.ok(canchas);
        }

        List<CanchaDTO> canchas = canchaService.listarCanchas(sedeId, tipoCanchaId);
        return ResponseEntity.ok(canchas);
    }

    @GetMapping("/con-disponibilidad")
    public ResponseEntity<List<CanchaConDisponibilidadDTO>> listarCanchasConDisponibilidad(
            @RequestParam(required = false) Long sedeId,
            @RequestParam(required = false) Long tipoCanchaId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<CanchaConDisponibilidadDTO> canchas = canchaService.listarCanchasConDisponibilidad(sedeId, tipoCanchaId, fecha);
        return ResponseEntity.ok(canchas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CanchaDTO> obtenerCancha(@PathVariable Long id) {
        return ResponseEntity.ok(canchaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<CanchaDTO> crearCancha(@RequestBody Cancha cancha) {
        return ResponseEntity.ok(canchaService.crear(cancha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CanchaDTO> actualizarCancha(@PathVariable Long id, @RequestBody Cancha cancha) {
        return ResponseEntity.ok(canchaService.actualizar(id, cancha));
    }
}
