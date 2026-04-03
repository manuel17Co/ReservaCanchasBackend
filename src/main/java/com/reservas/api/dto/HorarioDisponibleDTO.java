package com.reservas.api.dto;

import java.time.LocalTime;

public class HorarioDisponibleDTO {
    private Long id;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Long canchaId;
    private String canchaNombre;
    private Double precioHora;
    private String tipoCancha;
    private String sede;

    public HorarioDisponibleDTO() {}

    public HorarioDisponibleDTO(Long id, LocalTime horaInicio, LocalTime horaFin,
                                 Long canchaId, String canchaNombre, Double precioHora,
                                 String tipoCancha, String sede) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.canchaId = canchaId;
        this.canchaNombre = canchaNombre;
        this.precioHora = precioHora;
        this.tipoCancha = tipoCancha;
        this.sede = sede;
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

    public Double getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(Double precioHora) {
        this.precioHora = precioHora;
    }

    public String getTipoCancha() {
        return tipoCancha;
    }

    public void setTipoCancha(String tipoCancha) {
        this.tipoCancha = tipoCancha;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }
}
