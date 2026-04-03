package com.reservas.api.dto;

import java.time.LocalTime;

public class HorarioDTO {
    private Long id;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Boolean disponible;
    private Long canchaId;
    private String canchaNombre;

    public HorarioDTO() {}

    public HorarioDTO(Long id, LocalTime horaInicio, LocalTime horaFin, Boolean disponible,
                      Long canchaId, String canchaNombre) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.disponible = disponible;
        this.canchaId = canchaId;
        this.canchaNombre = canchaNombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Long getCanchaId() {
        return canchaId;
    }

    public void setCanchaId(Long canchaId) {
        this.canchaId = canchaId;
    }

    public String getCanchaNombre() {
        return canchaNombre;
    }

    public void setCanchaNombre(String canchaNombre) {
        this.canchaNombre = canchaNombre;
    }
}
