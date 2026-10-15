package com.prestamos.dao.impl;

import com.prestamos.config.ConexionBD;
import com.prestamos.dao.AnalistasDAO;
import com.prestamos.model.personal.Analistas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnalistasDAOImpl implements AnalistasDAO {

    private static final String SQL_COLUMNAS =
        "AnalistasCodigo, AnalistasPaterno, AnalistasMaterno, AnalistasNombres, "
      + "AnalistasNombreCompleto, AnalistasDNI, AnalistasCorreo, AnalistasTelefono, AnalistasEstado ";

    @Override
    public Analistas obtenerPorCodigo(String analistasCodigo) throws SQLException {
        String sql = "SELECT " + SQL_COLUMNAS + "FROM Personal.Analistas WHERE AnalistasCodigo = ?";

        try (Connection cn = ConexionBD.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, analistasCodigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearAnalista(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Analistas> listar() throws SQLException {
        List<Analistas> lista = new ArrayList<>();
        String sql = "SELECT " + SQL_COLUMNAS
                   + "FROM Personal.Analistas ORDER BY AnalistasPaterno, AnalistasMaterno";

        try (Connection cn = ConexionBD.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearAnalista(rs));
            }
        }
        return lista;
    }

    @Override
    public List<Analistas> listarActivos() throws SQLException {
        List<Analistas> lista = new ArrayList<>();
        String sql = "SELECT " + SQL_COLUMNAS
                   + "FROM Personal.Analistas WHERE AnalistasEstado = 'A' "
                   + "ORDER BY AnalistasPaterno, AnalistasMaterno";

        try (Connection cn = ConexionBD.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearAnalista(rs));
            }
        }
        return lista;
    }

    private Analistas mapearAnalista(ResultSet rs) throws SQLException {
        Analistas a = new Analistas();
        a.setAnalistasCodigo(rs.getString("AnalistasCodigo"));
        a.setAnalistasPaterno(rs.getString("AnalistasPaterno"));
        a.setAnalistasMaterno(rs.getString("AnalistasMaterno"));
        a.setAnalistasNombres(rs.getString("AnalistasNombres"));
        a.setAnalistasDNI(rs.getString("AnalistasDNI"));
        a.setAnalistasCorreo(rs.getString("AnalistasCorreo"));
        a.setAnalistasTelefono(rs.getString("AnalistasTelefono"));
        a.setAnalistasEstado(rs.getString("AnalistasEstado"));
        return a;
    }
}