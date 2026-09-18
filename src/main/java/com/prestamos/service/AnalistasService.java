package com.prestamos.service;

import com.prestamos.dao.AnalistasDAO;
import com.prestamos.exception.BusinessRuleException;
import com.prestamos.exception.DataAccessException;
import com.prestamos.exception.ResourceNotFoundException;
import com.prestamos.model.personal.Analistas;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class AnalistasService {

	private final AnalistasDAO analistasDAO;

	public AnalistasService(AnalistasDAO analistasDAO) {
		this.analistasDAO = Objects.requireNonNull(analistasDAO, "analistasDAO es obligatorio");
	}

	public Analistas obtenerPorCodigo(String codigo) {
		validarCodigo(codigo);
		try {
			Analistas analista = analistasDAO.obtenerPorCodigo(codigo.trim());
			if (analista == null) {
				throw new ResourceNotFoundException("No existe el analista solicitado");
			}
			return analista;
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudo consultar el analista", exception);
		}
	}

	public List<Analistas> listar() {
		try {
			return analistasDAO.listar();
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudo listar los analistas", exception);
		}
	}

	public List<Analistas> listarActivos() {
		try {
			return analistasDAO.listarActivos();
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudo listar los analistas activos", exception);
		}
	}

	private void validarCodigo(String codigo) {
		if (codigo == null || codigo.isBlank()) {
			throw new BusinessRuleException("El código del analista es obligatorio");
		}
	}
}
