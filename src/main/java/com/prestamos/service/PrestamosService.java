package com.prestamos.service;

import com.prestamos.dao.PrestamosDAO;
import com.prestamos.dao.impl.PrestamosDAOImpl;
import com.prestamos.model.prestamo.Prestamos;

/**
 *
 * @author LENOVO
 */
public class PrestamosService {

    private final PrestamosDAO prestamosDAO;

    public PrestamosService() {
        this.prestamosDAO = new PrestamosDAOImpl();
    }

    public String registrarPrestamo(Prestamos prestamo) {

        // =====================================================
        // Validar objeto
        // =====================================================

        if (prestamo == null) {

            throw new IllegalArgumentException(
                    "Los datos del préstamo son obligatorios."
            );
        }

        // =====================================================
        // Validar cliente
        // =====================================================

        if (prestamo.getClientesCodigo() == null
                || prestamo.getClientesCodigo().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Debe seleccionar un cliente."
            );
        }

        // =====================================================
        // Validar tipo de préstamo
        // =====================================================

        if (prestamo.getTiposPrestamoCodigo() == null
                || prestamo.getTiposPrestamoCodigo().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Debe seleccionar un tipo de préstamo."
            );
        }

        // =====================================================
        // Validar monto
        // =====================================================

        if (prestamo.getPrestamosMontoSolicitado() == null
                || prestamo.getPrestamosMontoSolicitado().signum() <= 0) {

            throw new IllegalArgumentException(
                    "El monto solicitado debe ser mayor que cero."
            );
        }

        // =====================================================
        // Validar plazo
        // =====================================================

        if (prestamo.getPrestamosPlazoCuotas() <= 0) {

            throw new IllegalArgumentException(
                    "El plazo debe ser mayor que cero."
            );
        }

        // =====================================================
        // Validar TEA
        // =====================================================

        if (prestamo.getPrestamosTEA() == null
                || prestamo.getPrestamosTEA().signum() < 0) {

            throw new IllegalArgumentException(
                    "La TEA no puede ser negativa."
            );
        }

        // =====================================================
        // Validar modalidad de pago
        // =====================================================

        if (prestamo.getModalidadesPagoCodigo() == null
                || prestamo.getModalidadesPagoCodigo().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Debe seleccionar una modalidad de pago."
            );
        }

        // =====================================================
        // Validar fecha
        // =====================================================

        if (prestamo.getPrestamosFechaDesembolso() == null) {

            throw new IllegalArgumentException(
                    "Debe ingresar la fecha de desembolso."
            );
        }

        // =====================================================
        // Validar moneda
        // =====================================================

        if (prestamo.getMonedasCodigo() == null
                || prestamo.getMonedasCodigo().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Debe seleccionar una moneda."
            );
        }

        // =====================================================
        // Validar analista
        // =====================================================

        if (prestamo.getAnalistasCodigo() == null
                || prestamo.getAnalistasCodigo().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Debe indicar el analista que registra."
            );
        }

        // =====================================================
        // Registrar mediante DAO
        // =====================================================

        return prestamosDAO.registrar(prestamo);
    }
}