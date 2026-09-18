package com.prestamos.dao;

import com.prestamos.model.cliente.Clientes;
import com.prestamos.model.cliente.Conyuges;
import com.prestamos.model.cliente.ReferenciasPersonales;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {

    String insertar(Clientes cliente) throws SQLException;

    void actualizar(Clientes cliente) throws SQLException;

    void desactivar(String clientesCodigo, String analistasCodigo, String motivo) throws SQLException;

    List<Clientes> listar(String estado) throws SQLException;

    Clientes obtenerPorCodigo(String clientesCodigo) throws SQLException;

    void guardarConyuge(Conyuges conyuge) throws SQLException;

    String insertarReferenciaPersonal(ReferenciasPersonales referencia) throws SQLException;

    void desactivarReferenciaPersonal(String referenciasPersonalesCodigo) throws SQLException;
}