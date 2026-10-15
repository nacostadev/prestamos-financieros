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

    private static final String SQL_GENERAR =
            "{call Cronograma.sp_Cronograma_Generar(?,?,?)}";

    private static final String SQL_OBTENER_POR_PRESTAMO =
            "{call Cronograma.sp_Cronograma_ObtenerPorPrestamo(?)}";

    @Override
    public String generar(String prestamosCodigo, String analistasCodigo)
            throws SQLException {

        try (Connection cn = ConexionBD.getConexion();
             CallableStatement cs = cn.prepareCall(SQL_GENERAR)) {

            cs.setString(1, prestamosCodigo);
            cs.setString(2, analistasCodigo);
            cs.registerOutParameter(3, Types.CHAR);

            cs.execute();

            String codigo = cs.getString(3);
            return codigo != null ? codigo.trim() : null;
        }
    }

    @Override
    public Cronogramas obtenerPorPrestamo(String prestamosCodigo)
            throws SQLException {

        try (Connection cn = ConexionBD.getConexion();
             CallableStatement cs =
                     cn.prepareCall(SQL_OBTENER_POR_PRESTAMO)) {

            cs.setString(1, prestamosCodigo);

            if (!cs.execute()) {
                return null;
            }

            Cronogramas cronograma;

            try (ResultSet rs = cs.getResultSet()) {
                if (rs == null || !rs.next()) {
                    return null;
                }

                cronograma = mapearCabecera(rs);
            }

            if (cs.getMoreResults()) {
                try (ResultSet rs = cs.getResultSet()) {
                    cronograma.setDetalle(mapearDetalles(rs));
                }
            }

            return cronograma;
        }
    }

    private Cronogramas mapearCabecera(ResultSet rs) throws SQLException {
        Cronogramas cronograma = new Cronogramas();

        cronograma.setCronogramasCodigo(
                rs.getString("CronogramasCodigo")
        );
        cronograma.setPrestamosCodigo(
                rs.getString("PrestamosCodigo")
        );
        cronograma.setCronogramasTEM(
                rs.getBigDecimal("CronogramasTEM")
        );
        cronograma.setCronogramasCuotaFija(
                rs.getBigDecimal("CronogramasCuotaFija")
        );
        cronograma.setCronogramasEstado(
                rs.getString("CronogramasEstado")
        );
        cronograma.setAnalistasCodigoGenera(
                rs.getString("AnalistasCodigoGenera")
        );

        Timestamp fecha = rs.getTimestamp("CronogramasFechaGeneracion");

        cronograma.setCronogramasFechaGeneracion(
                fecha != null ? fecha.toLocalDateTime() : null
        );

        return cronograma;
    }

    private List<DetalleCronograma> mapearDetalles(ResultSet rs)
            throws SQLException {

        List<DetalleCronograma> detalles = new ArrayList<>();

        while (rs.next()) {
            DetalleCronograma detalle = new DetalleCronograma();

            detalle.setDetalleCronogramaCodigo(
                    rs.getString("DetalleCronogramaCodigo")
            );
            detalle.setCronogramasCodigo(
                    rs.getString("CronogramasCodigo")
            );
            detalle.setDetalleNumeroCuota(
                    rs.getInt("NumeroCuota")
            );

            Date fecha = rs.getDate("FechaVencimiento");

            detalle.setDetalleFechaVencimiento(
                    fecha != null ? fecha.toLocalDate() : null
            );

            detalle.setDetalleSaldoAnterior(
                    rs.getBigDecimal("SaldoAnterior")
            );
            detalle.setDetalleInteres(
                    rs.getBigDecimal("Interes")
            );
            detalle.setDetalleAmortizacion(
                    rs.getBigDecimal("Amortizacion")
            );
            detalle.setDetalleCuota(
                    rs.getBigDecimal("Cuota")
            );
            detalle.setDetalleSaldoPendiente(
                    rs.getBigDecimal("SaldoPendiente")
            );

            detalles.add(detalle);
        }

        return detalles;
    }
}