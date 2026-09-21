package com.prestamos.dao.impl;

import com.prestamos.config.ConexionBD;
import com.prestamos.dao.PrestamosDAO;
import com.prestamos.model.prestamo.Prestamos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Types;

/**
 *
 * @author LENOVO
 */
public class PrestamosDAOImpl implements PrestamosDAO {

    @Override
    public String registrar(Prestamos prestamo) {

        String codigoPrestamo = null;

        String sql = "{CALL Prestamo.sp_Prestamo_Insertar(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

        try (Connection cn = ConexionBD.getConexion();
             CallableStatement cs = cn.prepareCall(sql)) {

            // =====================================================
            // 1. Código del cliente
            // =====================================================

            cs.setString(
                    1,
                    prestamo.getClientesCodigo()
            );

            // =====================================================
            // 2. Código del tipo de préstamo
            // =====================================================

            cs.setString(
                    2,
                    prestamo.getTiposPrestamoCodigo()
            );

            // =====================================================
            // 3. Monto solicitado
            // =====================================================

            cs.setBigDecimal(
                    3,
                    prestamo.getPrestamosMontoSolicitado()
            );

            // =====================================================
            // 4. Plazo en cuotas
            // =====================================================

            cs.setInt(
                    4,
                    prestamo.getPrestamosPlazoCuotas()
            );

            // =====================================================
            // 5. TEA
            // =====================================================

            cs.setBigDecimal(
                    5,
                    prestamo.getPrestamosTEA()
            );

            // =====================================================
            // 6. Modalidad de pago
            // =====================================================

            cs.setString(
                    6,
                    prestamo.getModalidadesPagoCodigo()
            );

            // =====================================================
            // 7. Fecha de desembolso
            // =====================================================

            cs.setDate(
                    7,
                    Date.valueOf(
                            prestamo.getPrestamosFechaDesembolso()
                    )
            );

            // =====================================================
            // 8. Moneda
            // =====================================================

            cs.setString(
                    8,
                    prestamo.getMonedasCodigo()
            );

            // =====================================================
            // 9. Garantía
            // =====================================================

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

            // =====================================================
            // 10. Observaciones
            // =====================================================

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

            // =====================================================
            // 11. Descripción del destino
            // =====================================================

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

            // =====================================================
            // 12. Analista que registra
            // =====================================================

            cs.setString(
                    12,
                    prestamo.getAnalistasCodigo()
            );

            // =====================================================
            // 13. Código generado por SQL Server
            // =====================================================

            cs.registerOutParameter(
                    13,
                    Types.NCHAR
            );

            // =====================================================
            // Ejecutar procedimiento almacenado
            // =====================================================

            cs.execute();

            // =====================================================
            // Obtener código generado
            // =====================================================

            codigoPrestamo = cs.getString(13);

            if (codigoPrestamo != null) {
                codigoPrestamo = codigoPrestamo.trim();
            }

        } catch (Exception e) {

            System.out.println(
                    "Error al registrar préstamo: "
                    + e.getMessage()
            );
        }

        return codigoPrestamo;
    }
}