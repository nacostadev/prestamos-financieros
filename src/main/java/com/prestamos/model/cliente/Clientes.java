package com.prestamos.model.cliente;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Clientes {

    private String clientesCodigo;
    private String tiposDocumentoCodigo;
    private String clientesNumeroDocumento;
    private String clientesPaterno;
    private String clientesMaterno;
    private String clientesNombres;
    private String clientesNombreCompleto;
    private LocalDate clientesFechaNacimiento;
    private String estadosCivilesCodigo;
    private String clientesDireccion;
    private String clientesTelefono;
    private String clientesCorreo;
    private String condicionesLaboralesCodigo;
    private BigDecimal clientesNivelIngresos;
    private String clientesObservaciones;
    private String clientesEstado;
    private String analistasCodigoRegistro;
    private String analistasCodigoModifica;
    private LocalDateTime clientesFechaRegistro;
    private LocalDateTime clientesFechaActualizacion;

    public Clientes() {
    }

    public Clientes(String clientesCodigo,
                    String tiposDocumentoCodigo,
                    String clientesNumeroDocumento,
                    String clientesPaterno,
                    String clientesMaterno,
                    String clientesNombres,
                    String clientesNombreCompleto,
                    LocalDate clientesFechaNacimiento,
                    String estadosCivilesCodigo,
                    String clientesDireccion,
                    String clientesTelefono,
                    String clientesCorreo,
                    String condicionesLaboralesCodigo,
                    BigDecimal clientesNivelIngresos,
                    String clientesObservaciones,
                    String clientesEstado,
                    String analistasCodigoRegistro,
                    String analistasCodigoModifica,
                    LocalDateTime clientesFechaRegistro,
                    LocalDateTime clientesFechaActualizacion) {

        this.clientesCodigo = clientesCodigo;
        this.tiposDocumentoCodigo = tiposDocumentoCodigo;
        this.clientesNumeroDocumento = clientesNumeroDocumento;
        this.clientesPaterno = clientesPaterno;
        this.clientesMaterno = clientesMaterno;
        this.clientesNombres = clientesNombres;
        this.clientesNombreCompleto = clientesNombreCompleto;
        this.clientesFechaNacimiento = clientesFechaNacimiento;
        this.estadosCivilesCodigo = estadosCivilesCodigo;
        this.clientesDireccion = clientesDireccion;
        this.clientesTelefono = clientesTelefono;
        this.clientesCorreo = clientesCorreo;
        this.condicionesLaboralesCodigo = condicionesLaboralesCodigo;
        this.clientesNivelIngresos = clientesNivelIngresos;
        this.clientesObservaciones = clientesObservaciones;
        this.clientesEstado = clientesEstado;
        this.analistasCodigoRegistro = analistasCodigoRegistro;
        this.analistasCodigoModifica = analistasCodigoModifica;
        this.clientesFechaRegistro = clientesFechaRegistro;
        this.clientesFechaActualizacion = clientesFechaActualizacion;
    }

    public String getClientesCodigo() {
        return clientesCodigo;
    }

    public void setClientesCodigo(String clientesCodigo) {
        this.clientesCodigo = clientesCodigo;
    }

    public String getTiposDocumentoCodigo() {
        return tiposDocumentoCodigo;
    }

    public void setTiposDocumentoCodigo(String tiposDocumentoCodigo) {
        this.tiposDocumentoCodigo = tiposDocumentoCodigo;
    }

    public String getClientesNumeroDocumento() {
        return clientesNumeroDocumento;
    }

    public void setClientesNumeroDocumento(String clientesNumeroDocumento) {
        this.clientesNumeroDocumento = clientesNumeroDocumento;
    }

    public String getClientesPaterno() {
        return clientesPaterno;
    }

    public void setClientesPaterno(String clientesPaterno) {
        this.clientesPaterno = clientesPaterno;
    }

    public String getClientesMaterno() {
        return clientesMaterno;
    }

    public void setClientesMaterno(String clientesMaterno) {
        this.clientesMaterno = clientesMaterno;
    }

    public String getClientesNombres() {
        return clientesNombres;
    }

    public void setClientesNombres(String clientesNombres) {
        this.clientesNombres = clientesNombres;
    }

    public String getClientesNombreCompleto() {
        return clientesNombreCompleto;
    }

    public LocalDate getClientesFechaNacimiento() {
        return clientesFechaNacimiento;
    }

    public void setClientesFechaNacimiento(LocalDate clientesFechaNacimiento) {
        this.clientesFechaNacimiento = clientesFechaNacimiento;
    }

    public String getEstadosCivilesCodigo() {
        return estadosCivilesCodigo;
    }

    public void setEstadosCivilesCodigo(String estadosCivilesCodigo) {
        this.estadosCivilesCodigo = estadosCivilesCodigo;
    }

    public String getClientesDireccion() {
        return clientesDireccion;
    }

    public void setClientesDireccion(String clientesDireccion) {
        this.clientesDireccion = clientesDireccion;
    }

    public String getClientesTelefono() {
        return clientesTelefono;
    }

    public void setClientesTelefono(String clientesTelefono) {
        this.clientesTelefono = clientesTelefono;
    }

    public String getClientesCorreo() {
        return clientesCorreo;
    }

    public void setClientesCorreo(String clientesCorreo) {
        this.clientesCorreo = clientesCorreo;
    }

    public String getCondicionesLaboralesCodigo() {
        return condicionesLaboralesCodigo;
    }

    public void setCondicionesLaboralesCodigo(String condicionesLaboralesCodigo) {
        this.condicionesLaboralesCodigo = condicionesLaboralesCodigo;
    }

    public BigDecimal getClientesNivelIngresos() {
        return clientesNivelIngresos;
    }

    public void setClientesNivelIngresos(BigDecimal clientesNivelIngresos) {
        this.clientesNivelIngresos = clientesNivelIngresos;
    }

    public String getClientesObservaciones() {
        return clientesObservaciones;
    }

    public void setClientesObservaciones(String clientesObservaciones) {
        this.clientesObservaciones = clientesObservaciones;
    }

    public String getClientesEstado() {
        return clientesEstado;
    }

    public void setClientesEstado(String clientesEstado) {
        this.clientesEstado = clientesEstado;
    }

    public String getAnalistasCodigoRegistro() {
        return analistasCodigoRegistro;
    }

    public void setAnalistasCodigoRegistro(String analistasCodigoRegistro) {
        this.analistasCodigoRegistro = analistasCodigoRegistro;
    }

    public String getAnalistasCodigoModifica() {
        return analistasCodigoModifica;
    }

    public void setAnalistasCodigoModifica(String analistasCodigoModifica) {
        this.analistasCodigoModifica = analistasCodigoModifica;
    }

    public LocalDateTime getClientesFechaRegistro() {
        return clientesFechaRegistro;
    }

    public void setClientesFechaRegistro(LocalDateTime clientesFechaRegistro) {
        this.clientesFechaRegistro = clientesFechaRegistro;
    }

    public LocalDateTime getClientesFechaActualizacion() {
        return clientesFechaActualizacion;
    }

    public void setClientesFechaActualizacion(LocalDateTime clientesFechaActualizacion) {
        this.clientesFechaActualizacion = clientesFechaActualizacion;
    }
}