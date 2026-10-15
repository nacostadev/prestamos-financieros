package com.prestamos.service;

import com.prestamos.dao.PrestamosDAO;
import com.prestamos.dao.impl.PrestamosDAOImpl;
import com.prestamos.exception.BusinessRuleException;
import com.prestamos.exception.DataAccessException;
import com.prestamos.model.prestamo.PoliticaCredito;
import com.prestamos.model.prestamo.Prestamos;
import com.prestamos.model.prestamo.TiposPrestamo;

import java.sql.SQLException;

public class PrestamosService {

    private final PrestamosDAO prestamosDAO;

    public PrestamosService() {
        this.prestamosDAO = new PrestamosDAOImpl();
    }

    public String registrarPrestamo(Prestamos prestamo) {

        if (prestamo == null) {

            throw new BusinessRuleException(
                    "Los datos del préstamo son obligatorios."
            );
        }

        if (prestamo.getClientesCodigo() == null
                || prestamo.getClientesCodigo().trim().isEmpty()) {

            throw new BusinessRuleException(
                    "Debe seleccionar un cliente."
            );
        }

        if (prestamo.getTiposPrestamoCodigo() == null
                || prestamo.getTiposPrestamoCodigo().trim().isEmpty()) {

            throw new BusinessRuleException(
                    "Debe seleccionar un tipo de préstamo."
            );
        }

        if (prestamo.getPrestamosMontoSolicitado() == null
                || prestamo.getPrestamosMontoSolicitado().signum() <= 0) {

            throw new BusinessRuleException(
                    "El monto solicitado debe ser mayor que cero."
            );
        }

        if (prestamo.getPrestamosPlazoCuotas() <= 0) {

            throw new BusinessRuleException(
                    "El plazo debe ser mayor que cero."
            );
        }

        if (prestamo.getPrestamosTEA() == null
                || prestamo.getPrestamosTEA().signum() < 0) {

            throw new BusinessRuleException(
                    "La TEA no puede ser negativa."
            );
        }

        if (prestamo.getModalidadesPagoCodigo() == null
                || prestamo.getModalidadesPagoCodigo().trim().isEmpty()) {

            throw new BusinessRuleException(
                    "Debe seleccionar una modalidad de pago."
            );
        }

        if (prestamo.getPrestamosFechaDesembolso() == null) {

            throw new BusinessRuleException(
                    "Debe ingresar la fecha de desembolso."
            );
        }

        if (prestamo.getMonedasCodigo() == null
                || prestamo.getMonedasCodigo().trim().isEmpty()) {

            throw new BusinessRuleException(
                    "Debe seleccionar una moneda."
            );
        }

        if (prestamo.getAnalistasCodigo() == null
                || prestamo.getAnalistasCodigo().trim().isEmpty()) {

            throw new BusinessRuleException(
                    "Debe indicar el analista que registra."
            );
        }

        try {

            if (!prestamosDAO.verificarClienteActivo(prestamo.getClientesCodigo().trim())) {
                throw new BusinessRuleException(
                        "El cliente no existe o no se encuentra activo."
                );
            }

            if (prestamosDAO.tienePrestamosEnMora(prestamo.getClientesCodigo().trim())) {
                throw new BusinessRuleException(
                        "El cliente tiene préstamos activos en estado de morosidad o incumplimiento."
                );
            }

            PoliticaCredito politica = prestamosDAO.obtenerPoliticaCredito(prestamo.getTiposPrestamoCodigo().trim());
            if (politica == null
                    || prestamo.getPrestamosMontoSolicitado().compareTo(politica.getPoliticaCreditoMontoMinimo()) < 0
                    || prestamo.getPrestamosMontoSolicitado().compareTo(politica.getPoliticaCreditoMontoMaximo()) > 0) {

                throw new BusinessRuleException(
                        "El monto solicitado no se encuentra dentro de los límites de la política crediticia del tipo de préstamo."
                );
            }

            TiposPrestamo tipo = prestamosDAO.obtenerTipoPrestamo(prestamo.getTiposPrestamoCodigo().trim());
            if (tipo == null
                    || prestamo.getPrestamosPlazoCuotas() < tipo.getTiposPrestamoPlazoMinimo()
                    || prestamo.getPrestamosPlazoCuotas() > tipo.getTiposPrestamoPlazoMaximo()
                    || prestamo.getPrestamosTEA().compareTo(tipo.getTiposPrestamoTasaMinima()) < 0
                    || prestamo.getPrestamosTEA().compareTo(tipo.getTiposPrestamoTasaMaxima()) > 0) {

                throw new BusinessRuleException(
                        "El plazo o la tasa no cumplen las condiciones del tipo de préstamo seleccionado."
                );
            }

            return prestamosDAO.registrar(prestamo);
        } catch (SQLException exception) {
            throw new DataAccessException("No fue posible registrar el préstamo financiero.", exception);
        }
    }
}