package com.prestamos.model.cliente;

public class Conyuges {

    private String conyugesCodigo;
    private String clientesCodigo;
    private String conyugesPaterno;
    private String conyugesMaterno;
    private String conyugesNombres;
    private String conyugesNombreCompleto;
    private String conyugesTelefono;
    private String conyugesEstado;

    public Conyuges() {
    }

    public Conyuges(String conyugesCodigo,
                    String clientesCodigo,
                    String conyugesPaterno,
                    String conyugesMaterno,
                    String conyugesNombres,
                    String conyugesNombreCompleto,
                    String conyugesTelefono,
                    String conyugesEstado) {

        this.conyugesCodigo = conyugesCodigo;
        this.clientesCodigo = clientesCodigo;
        this.conyugesPaterno = conyugesPaterno;
        this.conyugesMaterno = conyugesMaterno;
        this.conyugesNombres = conyugesNombres;
        this.conyugesNombreCompleto = conyugesNombreCompleto;
        this.conyugesTelefono = conyugesTelefono;
        this.conyugesEstado = conyugesEstado;
    }

    public String getConyugesCodigo() {
        return conyugesCodigo;
    }

    public void setConyugesCodigo(String conyugesCodigo) {
        this.conyugesCodigo = conyugesCodigo;
    }

    public String getClientesCodigo() {
        return clientesCodigo;
    }

    public void setClientesCodigo(String clientesCodigo) {
        this.clientesCodigo = clientesCodigo;
    }

    public String getConyugesPaterno() {
        return conyugesPaterno;
    }

    public void setConyugesPaterno(String conyugesPaterno) {
        this.conyugesPaterno = conyugesPaterno;
    }

    public String getConyugesMaterno() {
        return conyugesMaterno;
    }

    public void setConyugesMaterno(String conyugesMaterno) {
        this.conyugesMaterno = conyugesMaterno;
    }

    public String getConyugesNombres() {
        return conyugesNombres;
    }

    public void setConyugesNombres(String conyugesNombres) {
        this.conyugesNombres = conyugesNombres;
    }

    public String getConyugesNombreCompleto() {
        return conyugesNombreCompleto;
    }

    public String getConyugesTelefono() {
        return conyugesTelefono;
    }

    public void setConyugesTelefono(String conyugesTelefono) {
        this.conyugesTelefono = conyugesTelefono;
    }

    public String getConyugesEstado() {
        return conyugesEstado;
    }

    public void setConyugesEstado(String conyugesEstado) {
        this.conyugesEstado = conyugesEstado;
    }
}