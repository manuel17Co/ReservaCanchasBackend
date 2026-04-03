package com.reservas.api.controllers;

import com.reservas.api.dto.SedeDTO;
import com.reservas.api.models.Sede;
import com.reservas.api.services.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sedes")
@CrossOrigin(origins = "*")
public class SedeController {

    @Autowired
    private SedeService sedeService;

    @GetMapping
    public ResponseEntity<List<SedeDTO>> listarSedes() {
        return ResponseEntity.ok(sedeService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SedeDTO> obtenerSede(@PathVariable Long id) {
        return ResponseEntity.ok(sedeService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<SedeDTO> crearSede(@RequestBody Sede sede) {
        return ResponseEntity.ok(sedeService.crear(sede));
    }
}
