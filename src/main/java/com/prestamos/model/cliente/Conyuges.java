package com.prestamos.model.cliente;

public class Conyuges {

    private String conyugesCodigo;
    private String clientesCodigo;
    private String conyugesApellidos;
    private String conyugesNombres;
    private String conyugesTelefono;

    public Conyuges() {
    }

    public Conyuges(String conyugesCodigo,
                    String clientesCodigo,
                    String conyugesApellidos,
                    String conyugesNombres,
                    String conyugesTelefono) {

        this.conyugesCodigo = conyugesCodigo;
        this.clientesCodigo = clientesCodigo;
        this.conyugesApellidos = conyugesApellidos;
        this.conyugesNombres = conyugesNombres;
        this.conyugesTelefono = conyugesTelefono;
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

    public String getConyugesApellidos() {
        return conyugesApellidos;
    }

    public void setConyugesApellidos(String conyugesApellidos) {
        this.conyugesApellidos = conyugesApellidos;
    }

    public String getConyugesNombres() {
        return conyugesNombres;
    }

    public void setConyugesNombres(String conyugesNombres) {
        this.conyugesNombres = conyugesNombres;
    }

    public String getConyugesTelefono() {
        return conyugesTelefono;
    }

    public void setConyugesTelefono(String conyugesTelefono) {
        this.conyugesTelefono = conyugesTelefono;
    }
}
