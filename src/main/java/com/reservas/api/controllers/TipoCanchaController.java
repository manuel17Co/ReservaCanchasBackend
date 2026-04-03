package com.reservas.api.controllers;

import com.reservas.api.dto.TipoCanchaDTO;
import com.reservas.api.models.TipoCancha;
import com.reservas.api.services.TipoCanchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-cancha")
@CrossOrigin(origins = "*")
public class TipoCanchaController {

    @Autowired
    private TipoCanchaService tipoCanchaService;

    @GetMapping
    public ResponseEntity<List<TipoCanchaDTO>> listarTipos() {
        return ResponseEntity.ok(tipoCanchaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCanchaDTO> obtenerTipo(@PathVariable Long id) {
        return ResponseEntity.ok(tipoCanchaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<TipoCanchaDTO> crearTipo(@RequestBody TipoCancha tipoCancha) {
        return ResponseEntity.ok(tipoCanchaService.crear(tipoCancha));
    }
}
