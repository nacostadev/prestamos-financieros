package com.prestamos.model.cliente;

public class CondicionesLaborales {

    private String condicionesLaboralesCodigo;
    private String condicionesLaboralesNombre;

    public CondicionesLaborales() {
    }

    public CondicionesLaborales(String condicionesLaboralesCodigo,
                                String condicionesLaboralesNombre) {

        this.condicionesLaboralesCodigo = condicionesLaboralesCodigo;
        this.condicionesLaboralesNombre = condicionesLaboralesNombre;
    }

    public String getCondicionesLaboralesCodigo() {
        return condicionesLaboralesCodigo;
    }

    public void setCondicionesLaboralesCodigo(String condicionesLaboralesCodigo) {
        this.condicionesLaboralesCodigo = condicionesLaboralesCodigo;
    }

    public String getCondicionesLaboralesNombre() {
        return condicionesLaboralesNombre;
    }

    public void setCondicionesLaboralesNombre(String condicionesLaboralesNombre) {
        this.condicionesLaboralesNombre = condicionesLaboralesNombre;
    }
}
