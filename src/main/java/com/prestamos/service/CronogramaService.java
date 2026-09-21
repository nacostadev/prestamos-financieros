package com.prestamos.service;

import com.prestamos.dao.CronogramaDAO;
import com.prestamos.exception.BusinessRuleException;
import com.prestamos.exception.DataAccessException;
import com.prestamos.exception.ResourceNotFoundException;
import com.prestamos.model.cronograma.Cronogramas;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Objects;

@Service
public class CronogramaService {

	private final CronogramaDAO cronogramaDAO;

	public CronogramaService(CronogramaDAO cronogramaDAO) {
		this.cronogramaDAO = Objects.requireNonNull(cronogramaDAO, "cronogramaDAO es obligatorio");
	}

	public String generar(String prestamosCodigo, String analistasCodigo) {
		validarTexto(prestamosCodigo, "El préstamo es obligatorio");
		validarTexto(analistasCodigo, "El analista es obligatorio");
		try {
			return cronogramaDAO.generar(prestamosCodigo.trim(), analistasCodigo.trim());
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudo generar el cronograma", exception);
		}
	}

	public Cronogramas obtenerPorPrestamo(String prestamosCodigo) {
		validarTexto(prestamosCodigo, "El préstamo es obligatorio");
		try {
			Cronogramas cronograma = cronogramaDAO.obtenerPorPrestamo(prestamosCodigo.trim());
			if (cronograma == null) {
				throw new ResourceNotFoundException("No existe un cronograma activo para el préstamo indicado");
			}
			return cronograma;
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudo consultar el cronograma", exception);
		}
	}

	private void validarTexto(String valor, String mensaje) {
		if (valor == null || valor.isBlank()) {
			throw new BusinessRuleException(mensaje);
		}
	}
}
//hola probando