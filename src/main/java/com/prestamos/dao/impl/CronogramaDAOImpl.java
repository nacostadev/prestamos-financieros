package com.prestamos.dao.impl;

import com.prestamos.config.ConexionBD;
import com.prestamos.dao.CronogramaDAO;
import com.prestamos.model.cronograma.Cronogramas;
import com.prestamos.model.cronograma.DetalleCronograma;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class CronogramaDAOImpl implements CronogramaDAO {

    @Override
    public String generar(String prestamosCodigo, String analistasCodigo) throws SQLException {
        String sql = "{call Cronograma.sp_Cronograma_Generar(?,?,?)}";

        try (Connection cn = ConexionBD.getConexion();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, prestamosCodigo);
            cs.setString(2, analistasCodigo);
            cs.registerOutParameter(3, Types.CHAR);

            cs.execute();

            String codigoGenerado = cs.getString(3);
            return codigoGenerado != null ? codigoGenerado.trim() : null;
        }
    }

    @Override
    public Cronogramas obtenerPorPrestamo(String prestamosCodigo) throws SQLException {
        String sql = "{call Cronograma.sp_Cronograma_ObtenerPorPrestamo(?)}";

        try (Connection cn = ConexionBD.getConexion();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, prestamosCodigo);
            boolean hayResultados = cs.execute();

            if (!hayResultados) {
                return null;
            }

            Cronogramas cronograma = null;
            try (ResultSet rs = cs.getResultSet()) {
                if (rs.next()) {
                    cronograma = mapearCabecera(rs);
                }
            }

            if (cronograma == null) {
                return null;
            }

            if (cs.getMoreResults()) {
                try (ResultSet rs = cs.getResultSet()) {
                    List<DetalleCronograma> detalle = mapearDetalleList(rs);
                    cronograma.setDetalle(detalle);
                }
            }

            return cronograma;
        }
    }

    private Cronogramas mapearCabecera(ResultSet rs) throws SQLException {
        Cronogramas cronograma = new Cronogramas();
        cronograma.setCronogramasCodigo(rs.getString("CronogramasCodigo"));
        cronograma.setPrestamosCodigo(rs.getString("PrestamosCodigo"));
        cronograma.setCronogramasTEM(rs.getBigDecimal("CronogramasTEM"));
        cronograma.setCronogramasCuotaFija(rs.getBigDecimal("CronogramasCuotaFija"));

        Timestamp ts = rs.getTimestamp("CronogramasFechaGeneracion");
        cronograma.setCronogramasFechaGeneracion(ts != null ? ts.toLocalDateTime() : null);

        return cronograma;
    }

    private List<DetalleCronograma> mapearDetalleList(ResultSet rs) throws SQLException {
        List<DetalleCronograma> lista = new ArrayList<>();
        while (rs.next()) {
            DetalleCronograma d = new DetalleCronograma();
            d.setDetalleNumeroCuota(rs.getInt("NumeroCuota"));

            Date fechaVenc = rs.getDate("FechaVencimiento");
            d.setDetalleFechaVencimiento(fechaVenc != null ? fechaVenc.toLocalDate() : null);

            d.setDetalleSaldoAnterior(rs.getBigDecimal("SaldoAnterior"));
            d.setDetalleInteres(rs.getBigDecimal("Interes"));
            d.setDetalleAmortizacion(rs.getBigDecimal("Amortizacion"));
            d.setDetalleCuota(rs.getBigDecimal("Cuota"));
            d.setDetalleSaldoPendiente(rs.getBigDecimal("SaldoPendiente"));

            lista.add(d);
        }
        return lista;
    }
}