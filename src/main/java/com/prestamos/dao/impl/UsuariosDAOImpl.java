package com.prestamos.dao.impl;

import com.prestamos.config.ConexionBD;
import com.prestamos.dao.UsuariosDAO;
import com.prestamos.model.seguridad.Usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariosDAOImpl implements UsuariosDAO {

        private static final String CLAVE_ENCRIPTACION = "ClaveUsuarios";
        private static final String COLUMNAS_USUARIO = "UsuariosCodigo, UsuariosNombre, "
            + "UsuariosEstado, AnalistasCodigo";

    @Override
    public Usuarios buscarPorNombre(String usuariosNombre) throws SQLException {
        return buscarPorUsername(usuariosNombre);
    }

    @Override
    public Usuarios buscarPorUsername(String username) throws SQLException {
        String sql = "SELECT " + COLUMNAS_USUARIO + " FROM Seguridad.Usuarios WHERE UsuariosNombre = ?";
        return buscar(sql, username);
    }

    private Usuarios buscar(String sql, String value) throws SQLException {

        try (Connection cn = ConexionBD.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, value);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuarios u = new Usuarios();
                    u.setUsuariosCodigo(rs.getString("UsuariosCodigo"));
                    u.setUsuariosNombre(rs.getString("UsuariosNombre"));
                    u.setUsuariosEstado(rs.getString("UsuariosEstado"));
                    u.setAnalistasCodigo(rs.getString("AnalistasCodigo"));
                    return u;
                }
            }
        }
        return null;
    }

    @Override
    public boolean validarCredenciales(String usuariosNombre, String contrasenia) throws SQLException {
        String sql = "SELECT 1 FROM Seguridad.Usuarios "
                + "WHERE UsuariosNombre = ? AND UsuariosEstado = 'A' "
                + "AND CONVERT(NVARCHAR(100), DecryptByPassPhrase(?, UsuariosContrasenia)) = ?";
        try (Connection cn = ConexionBD.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, usuariosNombre);
            ps.setString(2, CLAVE_ENCRIPTACION);
            ps.setString(3, contrasenia);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public boolean esAnalistaCreditoActivo(String analistasCodigo) throws SQLException {
        String sql = "SELECT Seguridad.fn_EsAnalistaCreditoActivo(?) AS Resultado";

        try (Connection cn = ConexionBD.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, analistasCodigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("Resultado");
                }
            }
        }
        return false;
    }
}