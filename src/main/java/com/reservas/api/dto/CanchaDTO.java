package com.reservas.api.dto;

public class CanchaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precioHora;
    private Boolean activa;
    private Long sedeId;
    private String sedeNombre;
    private Long tipoCanchaId;
    private String tipoCanchaNombre;

    public CanchaDTO() {}

    public CanchaDTO(Long id, String nombre, String descripcion, Double precioHora, Boolean activa,
                     Long sedeId, String sedeNombre, Long tipoCanchaId, String tipoCanchaNombre) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioHora = precioHora;
        this.activa = activa;
        this.sedeId = sedeId;
        this.sedeNombre = sedeNombre;
        this.tipoCanchaId = tipoCanchaId;
        this.tipoCanchaNombre = tipoCanchaNombre;
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

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
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
}
