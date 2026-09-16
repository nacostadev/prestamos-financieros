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

    @Override
    public Usuarios buscarPorNombre(String usuariosNombre) throws SQLException {
        String sql = "SELECT UsuariosCodigo, UsuariosNombre, UsuariosEstado, AnalistasCodigo "
                   + "FROM Seguridad.Usuarios WHERE UsuariosNombre = ?";

        try (Connection cn = ConexionBD.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, usuariosNombre);
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
        String sql = "SELECT CONVERT(NVARCHAR(100), DecryptByPassPhrase(?, UsuariosContrasenia)) AS ContraseniaReal "
                   + "FROM Seguridad.Usuarios "
                   + "WHERE UsuariosNombre = ? AND UsuariosEstado = 'A'";

        try (Connection cn = ConexionBD.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, CLAVE_ENCRIPTACION);
            ps.setString(2, usuariosNombre);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String contraseniaReal = rs.getString("ContraseniaReal");
                    return contraseniaReal != null && contraseniaReal.equals(contrasenia);
                }
            }
        }
        return false;
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