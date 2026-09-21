package com.prestamos.model.prestamo;

/**
 *
 * @author LENOVO
 */
public class ModalidadesPago {

    private String modalidadesPagoCodigo;
    private String modalidadesPagoNombre;
    private String modalidadesPagoEstado;

    public ModalidadesPago() {
    }

    public ModalidadesPago(String modalidadesPagoCodigo,
                           String modalidadesPagoNombre,
                           String modalidadesPagoEstado) {
        this.modalidadesPagoCodigo = modalidadesPagoCodigo;
        this.modalidadesPagoNombre = modalidadesPagoNombre;
        this.modalidadesPagoEstado = modalidadesPagoEstado;
    }

    public String getModalidadesPagoCodigo() {
        return modalidadesPagoCodigo;
    }

    public void setModalidadesPagoCodigo(String modalidadesPagoCodigo) {
        this.modalidadesPagoCodigo = modalidadesPagoCodigo;
    }

    public String getModalidadesPagoNombre() {
        return modalidadesPagoNombre;
    }

    public void setModalidadesPagoNombre(String modalidadesPagoNombre) {
        this.modalidadesPagoNombre = modalidadesPagoNombre;
    }

    public String getModalidadesPagoEstado() {
        return modalidadesPagoEstado;
    }

    public void setModalidadesPagoEstado(String modalidadesPagoEstado) {
        this.modalidadesPagoEstado = modalidadesPagoEstado;
    }
}