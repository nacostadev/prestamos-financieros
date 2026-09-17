package com.prestamos.dao;

import com.prestamos.model.seguridad.Usuarios;
import java.sql.SQLException;

public interface UsuariosDAO {

    Usuarios buscarPorNombre(String usuariosNombre) throws SQLException;

    Usuarios buscarPorUsername(String username) throws SQLException;

    boolean validarCredenciales(String usuariosNombre, String contrasenia) throws SQLException;

    boolean esAnalistaCreditoActivo(String analistasCodigo) throws SQLException;
}