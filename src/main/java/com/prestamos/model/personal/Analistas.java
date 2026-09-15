package com.prestamos.model.personal;

public class Analistas {

    private String analistasCodigo;
    private String analistasPaterno;
    private String analistasMaterno;
    private String analistasNombres;
    private String analistasNombreCompleto;
    private String analistasDNI;
    private String analistasCorreo;
    private String analistasTelefono;
    private String analistasEstado;

    public Analistas() {
    }

    public Analistas(String analistasCodigo,
                     String analistasPaterno,
                     String analistasMaterno,
                     String analistasNombres,
                     String analistasNombreCompleto,
                     String analistasDNI,
                     String analistasCorreo,
                     String analistasTelefono,
                     String analistasEstado) {

        this.analistasCodigo = analistasCodigo;
        this.analistasPaterno = analistasPaterno;
        this.analistasMaterno = analistasMaterno;
        this.analistasNombres = analistasNombres;
        this.analistasNombreCompleto = analistasNombreCompleto;
        this.analistasDNI = analistasDNI;
        this.analistasCorreo = analistasCorreo;
        this.analistasTelefono = analistasTelefono;
        this.analistasEstado = analistasEstado;
    }

    public String getAnalistasCodigo() {
        return analistasCodigo;
    }

    public void setAnalistasCodigo(String analistasCodigo) {
        this.analistasCodigo = analistasCodigo;
    }

    public String getAnalistasPaterno() {
        return analistasPaterno;
    }

    public void setAnalistasPaterno(String analistasPaterno) {
        this.analistasPaterno = analistasPaterno;
    }

    public String getAnalistasMaterno() {
        return analistasMaterno;
    }

    public void setAnalistasMaterno(String analistasMaterno) {
        this.analistasMaterno = analistasMaterno;
    }

    public String getAnalistasNombres() {
        return analistasNombres;
    }

    public void setAnalistasNombres(String analistasNombres) {
        this.analistasNombres = analistasNombres;
    }

    public String getAnalistasNombreCompleto() {
        return analistasNombreCompleto;
    }

    public String getAnalistasDNI() {
        return analistasDNI;
    }

    public void setAnalistasDNI(String analistasDNI) {
        this.analistasDNI = analistasDNI;
    }

    public String getAnalistasCorreo() {
        return analistasCorreo;
    }

    public void setAnalistasCorreo(String analistasCorreo) {
        this.analistasCorreo = analistasCorreo;
    }

    public String getAnalistasTelefono() {
        return analistasTelefono;
    }

    public void setAnalistasTelefono(String analistasTelefono) {
        this.analistasTelefono = analistasTelefono;
    }

    public String getAnalistasEstado() {
        return analistasEstado;
    }

    public void setAnalistasEstado(String analistasEstado) {
        this.analistasEstado = analistasEstado;
    }
}
