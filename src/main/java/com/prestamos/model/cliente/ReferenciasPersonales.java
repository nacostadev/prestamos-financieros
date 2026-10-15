package com.prestamos.model.cliente;

public class ReferenciasPersonales {

    private String referenciasPersonalesCodigo;
    private String clientesCodigo;
    private String referenciasPersonalesNombres;
    private String referenciasPersonalesTelefono;
    private String referenciasPersonalesRelacion;
    private String referenciasPersonalesEstado;

    public ReferenciasPersonales() {
    }

    public ReferenciasPersonales(String referenciasPersonalesCodigo,
                                 String clientesCodigo,
                                 String referenciasPersonalesNombres,
                                 String referenciasPersonalesTelefono,
                                 String referenciasPersonalesRelacion,
                                 String referenciasPersonalesEstado) {

        this.referenciasPersonalesCodigo = referenciasPersonalesCodigo;
        this.clientesCodigo = clientesCodigo;
        this.referenciasPersonalesNombres = referenciasPersonalesNombres;
        this.referenciasPersonalesTelefono = referenciasPersonalesTelefono;
        this.referenciasPersonalesRelacion = referenciasPersonalesRelacion;
        this.referenciasPersonalesEstado = referenciasPersonalesEstado;
    }

    public String getReferenciasPersonalesCodigo() {
        return referenciasPersonalesCodigo;
    }

    public void setReferenciasPersonalesCodigo(String referenciasPersonalesCodigo) {
        this.referenciasPersonalesCodigo = referenciasPersonalesCodigo;
    }

    public String getClientesCodigo() {
        return clientesCodigo;
    }

    public void setClientesCodigo(String clientesCodigo) {
        this.clientesCodigo = clientesCodigo;
    }

    public String getReferenciasPersonalesNombres() {
        return referenciasPersonalesNombres;
    }

    public void setReferenciasPersonalesNombres(String referenciasPersonalesNombres) {
        this.referenciasPersonalesNombres = referenciasPersonalesNombres;
    }

    public String getReferenciasPersonalesTelefono() {
        return referenciasPersonalesTelefono;
    }

    public void setReferenciasPersonalesTelefono(String referenciasPersonalesTelefono) {
        this.referenciasPersonalesTelefono = referenciasPersonalesTelefono;
    }

    public String getReferenciasPersonalesRelacion() {
        return referenciasPersonalesRelacion;
    }

    public void setReferenciasPersonalesRelacion(String referenciasPersonalesRelacion) {
        this.referenciasPersonalesRelacion = referenciasPersonalesRelacion;
    }

    public String getReferenciasPersonalesEstado() {
        return referenciasPersonalesEstado;
    }

    public void setReferenciasPersonalesEstado(String referenciasPersonalesEstado) {
        this.referenciasPersonalesEstado = referenciasPersonalesEstado;
    }
}