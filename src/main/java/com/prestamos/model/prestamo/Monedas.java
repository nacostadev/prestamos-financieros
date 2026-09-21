package com.prestamos.model.prestamo;

/**
 *
 * @author LENOVO
 */
public class Monedas {

    private String monedasCodigo;
    private String monedasNombre;
    private String monedasSimbolo;
    private String monedasEstado;

    public Monedas() {
    }

    public Monedas(String monedasCodigo,
                   String monedasNombre,
                   String monedasSimbolo,
                   String monedasEstado) {
        this.monedasCodigo = monedasCodigo;
        this.monedasNombre = monedasNombre;
        this.monedasSimbolo = monedasSimbolo;
        this.monedasEstado = monedasEstado;
    }

    public String getMonedasCodigo() {
        return monedasCodigo;
    }

    public void setMonedasCodigo(String monedasCodigo) {
        this.monedasCodigo = monedasCodigo;
    }

    public String getMonedasNombre() {
        return monedasNombre;
    }

    public void setMonedasNombre(String monedasNombre) {
        this.monedasNombre = monedasNombre;
    }

    public String getMonedasSimbolo() {
        return monedasSimbolo;
    }

    public void setMonedasSimbolo(String monedasSimbolo) {
        this.monedasSimbolo = monedasSimbolo;
    }

    public String getMonedasEstado() {
        return monedasEstado;
    }

    public void setMonedasEstado(String monedasEstado) {
        this.monedasEstado = monedasEstado;
    }
}