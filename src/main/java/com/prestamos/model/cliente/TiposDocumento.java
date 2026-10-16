package com.prestamos.model.cliente;

public class TiposDocumento {

    private String tiposDocumentoCodigo;
    private String tiposDocumentoNombre;

    public TiposDocumento() {
    }

    public TiposDocumento(String tiposDocumentoCodigo, String tiposDocumentoNombre) {

        this.tiposDocumentoCodigo = tiposDocumentoCodigo;
        this.tiposDocumentoNombre = tiposDocumentoNombre;
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
}
