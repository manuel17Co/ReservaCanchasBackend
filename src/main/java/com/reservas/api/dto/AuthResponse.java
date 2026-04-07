package com.reservas.api.dto;

public class AuthResponse {

    private String token;
    private String mensaje;
    private String nombre; // Nuevo campo
    private String correo; // Nuevo campo

    public AuthResponse() {}

    // Actualizamos el constructor para recibir los nuevos datos
    public AuthResponse(String token, String mensaje, String nombre, String correo) {
        this.token = token;
        this.mensaje = mensaje;
        this.nombre = nombre;
        this.correo = correo;
    }

    // Getters y Setters para los campos existentes
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    // Nuevos Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}