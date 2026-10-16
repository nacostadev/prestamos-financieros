package com.prestamos.model.personal;

public class Analistas {

    private String analistasCodigo;
    private String analistasApellidos;
    private String analistasNombres;

    public Analistas() {
    }

    public Analistas(String analistasCodigo,
                     String analistasApellidos,
                     String analistasNombres) {

        this.analistasCodigo = analistasCodigo;
        this.analistasApellidos = analistasApellidos;
        this.analistasNombres = analistasNombres;
    }

    public String getAnalistasCodigo() {
        return analistasCodigo;
    }

    public void setAnalistasCodigo(String analistasCodigo) {
        this.analistasCodigo = analistasCodigo;
    }

    public String getAnalistasApellidos() {
        return analistasApellidos;
    }

    public void setAnalistasApellidos(String analistasApellidos) {
        this.analistasApellidos = analistasApellidos;
    }

    public String getAnalistasNombres() {
        return analistasNombres;
    }

    public void setAnalistasNombres(String analistasNombres) {
        this.analistasNombres = analistasNombres;
    }
}
