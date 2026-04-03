package com.reservas.api.dto;

public class CanchaConDisponibilidadDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precioHora;
    private Long sedeId;
    private String sedeNombre;
    private Long tipoCanchaId;
    private String tipoCanchaNombre;
    private Boolean tieneDisponibilidad;

    public CanchaConDisponibilidadDTO() {}

    public CanchaConDisponibilidadDTO(Long id, String nombre, String descripcion, Double precioHora,
                                       Long sedeId, String sedeNombre, Long tipoCanchaId, String tipoCanchaNombre,
                                       Boolean tieneDisponibilidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioHora = precioHora;
        this.sedeId = sedeId;
        this.sedeNombre = sedeNombre;
        this.tipoCanchaId = tipoCanchaId;
        this.tipoCanchaNombre = tipoCanchaNombre;
        this.tieneDisponibilidad = tieneDisponibilidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(Double precioHora) {
        this.precioHora = precioHora;
    }

    public Long getSedeId() {
        return sedeId;
    }

    public void setSedeId(Long sedeId) {
        this.sedeId = sedeId;
    }

    public String getSedeNombre() {
        return sedeNombre;
    }

    public void setSedeNombre(String sedeNombre) {
        this.sedeNombre = sedeNombre;
    }

    public Long getTipoCanchaId() {
        return tipoCanchaId;
    }

    public void setTipoCanchaId(Long tipoCanchaId) {
        this.tipoCanchaId = tipoCanchaId;
    }

    public String getTipoCanchaNombre() {
        return tipoCanchaNombre;
    }

    public void setTipoCanchaNombre(String tipoCanchaNombre) {
        this.tipoCanchaNombre = tipoCanchaNombre;
    }

    public Boolean getTieneDisponibilidad() {
        return tieneDisponibilidad;
    }

    public void setTieneDisponibilidad(Boolean tieneDisponibilidad) {
        this.tieneDisponibilidad = tieneDisponibilidad;
    }
}
