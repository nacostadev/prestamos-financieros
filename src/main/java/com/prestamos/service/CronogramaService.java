package com.prestamos.service;

import com.prestamos.dao.CronogramaDAO;
import com.prestamos.exception.BusinessRuleException;
import com.prestamos.exception.DataAccessException;
import com.prestamos.exception.ResourceNotFoundException;
import com.prestamos.model.cronograma.Cronogramas;
import com.prestamos.model.cronograma.DetalleCronograma;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class CronogramaService {

    private final CronogramaDAO cronogramaDAO;

    public CronogramaService(CronogramaDAO cronogramaDAO) {
        this.cronogramaDAO = Objects.requireNonNull(cronogramaDAO, "cronogramaDAO es obligatorio");
    }

    public String generar(String prestamosCodigo, String analistasCodigo) {
        validarTexto(prestamosCodigo, "El código del préstamo es obligatorio");
        validarTexto(analistasCodigo, "El código del analista es obligatorio");

        try {
            String codigoGenerado = cronogramaDAO.generar(prestamosCodigo.trim(), analistasCodigo.trim());

            // Si la base de datos no retorna código, no cumple reglas como estado VIGENTE (RN-01)
            if (codigoGenerado == null || codigoGenerado.isBlank()) {
                throw new BusinessRuleException("No se pudo generar el cronograma. Verifique que el préstamo se encuentre en estado VIGENTE.");
            }

            // Validar consistencia financiera post-generación (CA-07)
            Cronogramas cronograma = cronogramaDAO.obtenerPorPrestamo(prestamosCodigo.trim());
            if (cronograma != null) {
                validarConsistenciaCuotas(cronograma);
            }

            return codigoGenerado;

        } catch (SQLException exception) {
            throw new DataAccessException("No se pudo generar el cronograma debido a un error técnico en la base de datos.", exception);
        }
    }

    public Cronogramas obtenerPorPrestamo(String prestamosCodigo) {
        validarTexto(prestamosCodigo, "El código del préstamo es obligatorio");

        try {
            Cronogramas cronograma = cronogramaDAO.obtenerPorPrestamo(prestamosCodigo.trim());
            if (cronograma == null) {
                throw new ResourceNotFoundException("No existe un cronograma activo para el préstamo indicado.");
            }
            return cronograma;
        } catch (SQLException exception) {
            throw new DataAccessException("No se pudo consultar el cronograma en la base de datos.", exception);
        }
    }

    private void validarTexto(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new BusinessRuleException(mensaje);
        }
    }

    private void validarConsistenciaCuotas(Cronogramas cronograma) {
        List<DetalleCronograma> detalles = cronograma.getDetalle();
        if (detalles != null && !detalles.isEmpty()) {
            DetalleCronograma ultimaCuota = detalles.get(detalles.size() - 1);
            // Verifica que la última cuota deje el saldo en 0.00
            if (ultimaCuota.getDetalleSaldoPendiente() != null 
                    && ultimaCuota.getDetalleSaldoPendiente().compareTo(new BigDecimal("0.05")) > 0) {
                throw new BusinessRuleException("Advertencia de consistencia: El saldo final del cronograma generado no liquida a cero.");
            }
        }
    }
}