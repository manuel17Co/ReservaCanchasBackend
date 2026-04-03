package com.reservas.api.controllers;

import com.reservas.api.dto.HorarioDisponibleDTO;
import com.reservas.api.dto.HorarioDTO;
import com.reservas.api.models.Horario;
import com.reservas.api.services.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/horarios")
@CrossOrigin(origins = "*")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @GetMapping("/cancha/{canchaId}")
    public ResponseEntity<List<HorarioDTO>> listarPorCancha(@PathVariable Long canchaId) {
        return ResponseEntity.ok(horarioService.listarPorCancha(canchaId));
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<HorarioDisponibleDTO>> listarHorariosDisponibles(
            @RequestParam Long canchaId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<HorarioDisponibleDTO> horarios = horarioService.listarHorariosDisponibles(canchaId, fecha);
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioDTO> obtenerHorario(@PathVariable Long id) {
        return ResponseEntity.ok(horarioService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<HorarioDTO> crearHorario(@RequestBody Horario horario) {
        return ResponseEntity.ok(horarioService.crear(horario));
    }
}
