package com.prestamos.dao;

import com.prestamos.model.prestamo.PoliticaCredito;
import com.prestamos.model.prestamo.Prestamos;
import com.prestamos.model.prestamo.TiposPrestamo;

import java.sql.SQLException;

public interface PrestamosDAO {

    String registrar(Prestamos prestamo) throws SQLException;

    boolean verificarClienteActivo(String clientesCodigo) throws SQLException;

    boolean tienePrestamosEnMora(String clientesCodigo) throws SQLException;

    PoliticaCredito obtenerPoliticaCredito(String tiposPrestamoCodigo) throws SQLException;

    TiposPrestamo obtenerTipoPrestamo(String tiposPrestamoCodigo) throws SQLException;

}