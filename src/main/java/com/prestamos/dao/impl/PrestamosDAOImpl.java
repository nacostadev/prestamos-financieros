package com.prestamos.dao.impl;

import com.prestamos.config.ConexionBD;
import com.prestamos.dao.PrestamosDAO;
import com.prestamos.exception.DataAccessException;
import com.prestamos.model.prestamo.PoliticaCredito;
import com.prestamos.model.prestamo.Prestamos;
import com.prestamos.model.prestamo.TiposPrestamo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class PrestamosDAOImpl implements PrestamosDAO {

    @Override
    public String registrar(Prestamos prestamo) throws SQLException {

        String codigoPrestamo = null;

        String sql = "{CALL Prestamo.sp_Prestamo_Insertar(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

        try (Connection cn = ConexionBD.getConexion();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(
                    1,
                    prestamo.getClientesCodigo()
            );

            cs.setString(
                    2,
                    prestamo.getTiposPrestamoCodigo()
            );

            cs.setBigDecimal(
                    3,
                    prestamo.getPrestamosMontoSolicitado()
            );

            cs.setInt(
                    4,
                    prestamo.getPrestamosPlazoCuotas()
            );

            cs.setBigDecimal(
                    5,
                    prestamo.getPrestamosTEA()
            );

            cs.setString(
                    6,
                    prestamo.getModalidadesPagoCodigo()
            );

            cs.setDate(
                    7,
                    Date.valueOf(
                            prestamo.getPrestamosFechaDesembolso()
                    )
            );

            cs.setString(
                    8,
                    prestamo.getMonedasCodigo()
            );

            if (prestamo.getGarantiasCodigo() == null
                    || prestamo.getGarantiasCodigo().trim().isEmpty()) {

                cs.setNull(
                        9,
                        Types.NCHAR
                );

            } else {

                cs.setString(
                        9,
                        prestamo.getGarantiasCodigo()
                );
            }

            if (prestamo.getPrestamosObservaciones() == null
                    || prestamo.getPrestamosObservaciones().trim().isEmpty()) {

                cs.setNull(
                        10,
                        Types.NVARCHAR
                );

            } else {

                cs.setString(
                        10,
                        prestamo.getPrestamosObservaciones()
                );
            }

            if (prestamo.getPrestamosDescripcionDestino() == null
                    || prestamo.getPrestamosDescripcionDestino().trim().isEmpty()) {

                cs.setNull(
                        11,
                        Types.NVARCHAR
                );

            } else {

                cs.setString(
                        11,
                        prestamo.getPrestamosDescripcionDestino()
                );
            }

            cs.setString(
                    12,
                    prestamo.getAnalistasCodigo()
            );

            cs.registerOutParameter(
                    13,
                    Types.NCHAR
            );

            cs.execute();

            codigoPrestamo = cs.getString(13);

            if (codigoPrestamo != null) {
                codigoPrestamo = codigoPrestamo.trim();
            }

        } catch (SQLException e) {
            throw new DataAccessException("No se pudo registrar el préstamo financiero.", e);
        }

        return codigoPrestamo;
    }

    @Override
    public boolean verificarClienteActivo(String clientesCodigo) throws SQLException {
        String sql = "SELECT 1 FROM Cliente.Clientes WHERE ClientesCodigo = ? AND ClientesEstado = 'A'";

        try (Connection cn = ConexionBD.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, clientesCodigo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public boolean tienePrestamosEnMora(String clientesCodigo) throws SQLException {
        String sql = "SELECT 1 FROM Prestamo.Prestamos "
                + "WHERE ClientesCodigo = ? AND EstadosPrestamoCodigo IN ('EP03', 'EP04')";

        try (Connection cn = ConexionBD.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, clientesCodigo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public PoliticaCredito obtenerPoliticaCredito(String tiposPrestamoCodigo) throws SQLException {
        String sql = "SELECT PoliticaCreditoCodigo, TiposPrestamoCodigo, PoliticaCreditoMontoMinimo, "
                + "PoliticaCreditoMontoMaximo, PoliticaCreditoEstado "
                + "FROM Prestamo.PoliticaCredito "
                + "WHERE TiposPrestamoCodigo = ? AND PoliticaCreditoEstado = 'A'";

        try (Connection cn = ConexionBD.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, tiposPrestamoCodigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                PoliticaCredito politica = new PoliticaCredito();
                politica.setPoliticaCreditoCodigo(rs.getString("PoliticaCreditoCodigo"));
                politica.setTiposPrestamoCodigo(rs.getString("TiposPrestamoCodigo"));
                politica.setPoliticaCreditoMontoMinimo(rs.getBigDecimal("PoliticaCreditoMontoMinimo"));
                politica.setPoliticaCreditoMontoMaximo(rs.getBigDecimal("PoliticaCreditoMontoMaximo"));
                politica.setPoliticaCreditoEstado(rs.getString("PoliticaCreditoEstado"));
                return politica;
            }
        }
    }

    @Override
    public TiposPrestamo obtenerTipoPrestamo(String tiposPrestamoCodigo) throws SQLException {
        String sql = "SELECT TiposPrestamoCodigo, TiposPrestamoNombre, TiposPrestamoPlazoMinimo, "
                + "TiposPrestamoPlazoMaximo, TiposPrestamoTasaMinima, TiposPrestamoTasaMaxima, "
                + "TiposPrestamoEstado "
                + "FROM Prestamo.TiposPrestamo "
                + "WHERE TiposPrestamoCodigo = ? AND TiposPrestamoEstado = 'A'";

        try (Connection cn = ConexionBD.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, tiposPrestamoCodigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                TiposPrestamo tipo = new TiposPrestamo();
                tipo.setTiposPrestamoCodigo(rs.getString("TiposPrestamoCodigo"));
                tipo.setTiposPrestamoNombre(rs.getString("TiposPrestamoNombre"));
                tipo.setTiposPrestamoPlazoMinimo(rs.getInt("TiposPrestamoPlazoMinimo"));
                tipo.setTiposPrestamoPlazoMaximo(rs.getInt("TiposPrestamoPlazoMaximo"));
                tipo.setTiposPrestamoTasaMinima(rs.getBigDecimal("TiposPrestamoTasaMinima"));
                tipo.setTiposPrestamoTasaMaxima(rs.getBigDecimal("TiposPrestamoTasaMaxima"));
                tipo.setTiposPrestamoEstado(rs.getString("TiposPrestamoEstado"));
                return tipo;
            }
        }
    }
}