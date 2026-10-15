package com.prestamos.model.cliente;

public class TiposDocumento {

    private String tiposDocumentoCodigo;
    private String tiposDocumentoNombre;
    private String tiposDocumentoEstado;

    public TiposDocumento() {
    }

    public TiposDocumento(String tiposDocumentoCodigo,
                          String tiposDocumentoNombre,
                          String tiposDocumentoEstado) {

        this.tiposDocumentoCodigo = tiposDocumentoCodigo;
        this.tiposDocumentoNombre = tiposDocumentoNombre;
        this.tiposDocumentoEstado = tiposDocumentoEstado;
    }

    public String getTiposDocumentoCodigo() {
        return tiposDocumentoCodigo;
    }

    public void setTiposDocumentoCodigo(String tiposDocumentoCodigo) {
        this.tiposDocumentoCodigo = tiposDocumentoCodigo;
    }

    public String getTiposDocumentoNombre() {
        return tiposDocumentoNombre;
    }

    public void setTiposDocumentoNombre(String tiposDocumentoNombre) {
        this.tiposDocumentoNombre = tiposDocumentoNombre;
    }

    public String getTiposDocumentoEstado() {
        return tiposDocumentoEstado;
    }

    public void setTiposDocumentoEstado(String tiposDocumentoEstado) {
        this.tiposDocumentoEstado = tiposDocumentoEstado;
    }
}