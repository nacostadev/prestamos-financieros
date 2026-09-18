package com.prestamos.dao;

import com.prestamos.model.seguridad.Roles;
import java.sql.SQLException;
import java.util.List;

public interface UsuarioRolDAO {

    List<Roles> obtenerRolesPorUsuarioId(String usuarioCodigo) throws SQLException;
}
