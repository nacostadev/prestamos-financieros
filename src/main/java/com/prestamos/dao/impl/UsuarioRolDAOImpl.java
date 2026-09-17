package com.prestamos.dao.impl;

import com.prestamos.config.ConexionBD;
import com.prestamos.dao.UsuarioRolDAO;
import com.prestamos.model.seguridad.Roles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRolDAOImpl implements UsuarioRolDAO {

    @Override
    public List<Roles> obtenerRolesPorUsuarioId(String usuarioCodigo) throws SQLException {
        String sql = "SELECT r.RolesCodigo, r.RolesNombre, r.RolesDescripcion, "
                + "r.RolesFechaCreacion, r.RolesEstado "
                + "FROM Seguridad.DetalleRoles dr "
                + "INNER JOIN Seguridad.Roles r ON r.RolesCodigo = dr.RolesCodigo "
                + "WHERE dr.UsuariosCodigo = ? AND dr.DetallesRolesEstado = 'A' "
                + "AND r.RolesEstado = 'A' ORDER BY r.RolesNombre";
        List<Roles> roles = new ArrayList<>();
        try (Connection connection = ConexionBD.getConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuarioCodigo);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    roles.add(mapearRol(resultSet));
                }
            }
        }
        return roles;
    }

    private Roles mapearRol(ResultSet resultSet) throws SQLException {
        Roles role = new Roles();
        role.setRolesCodigo(resultSet.getString("RolesCodigo"));
        role.setRolesNombre(resultSet.getString("RolesNombre"));
        role.setRolesDescripcion(resultSet.getString("RolesDescripcion"));
        java.sql.Date creationDate = resultSet.getDate("RolesFechaCreacion");
        role.setRolesFechaCreacion(creationDate == null ? null : creationDate.toLocalDate());
        role.setRolesEstado(resultSet.getString("RolesEstado"));
        return role;
    }
}
