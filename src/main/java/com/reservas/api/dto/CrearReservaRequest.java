package com.reservas.api.dto;

import java.time.LocalDate;

public class CrearReservaRequest {
    private Long horarioId;
    private LocalDate fecha;

    public CrearReservaRequest() {}

    public CrearReservaRequest(Long horarioId, LocalDate fecha) {
        this.horarioId = horarioId;
        this.fecha = fecha;
    }

    public Long getHorarioId() {
        return horarioId;
    }

    public void setHorarioId(Long horarioId) {
        this.horarioId = horarioId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
