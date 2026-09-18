package com.prestamos.service;

import com.prestamos.dao.UsuariosDAO;
import com.prestamos.exception.BusinessRuleException;
import com.prestamos.exception.DataAccessException;
import com.prestamos.exception.ResourceNotFoundException;
import com.prestamos.model.seguridad.Usuarios;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Objects;

@Service
public class UsuariosService {

	private final UsuariosDAO usuariosDAO;

	public UsuariosService(UsuariosDAO usuariosDAO) {
		this.usuariosDAO = Objects.requireNonNull(usuariosDAO, "usuariosDAO es obligatorio");
	}

	public Usuarios buscarPorNombre(String nombre) {
		validarTexto(nombre, "El nombre de usuario es obligatorio");
		try {
			Usuarios usuario = usuariosDAO.buscarPorNombre(nombre.trim());
			if (usuario == null) {
				throw new ResourceNotFoundException("No existe el usuario solicitado");
			}
			return usuario;
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudo consultar el usuario", exception);
		}
	}

	public boolean validarCredenciales(String nombre, String contrasenia) {
		validarTexto(nombre, "El nombre de usuario es obligatorio");
		validarTexto(contrasenia, "La contraseña es obligatoria");
		try {
			return usuariosDAO.validarCredenciales(nombre.trim(), contrasenia);
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudieron validar las credenciales", exception);
		}
	}

	public boolean autenticar(String nombre, String contrasenia) {
		return validarCredenciales(nombre, contrasenia);
	}

	public boolean esAnalistaCreditoActivo(String codigoAnalista) {
		validarTexto(codigoAnalista, "El código del analista es obligatorio");
		try {
			return usuariosDAO.esAnalistaCreditoActivo(codigoAnalista.trim());
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudo validar el estado del analista", exception);
		}
	}

	private void validarTexto(String valor, String mensaje) {
		if (valor == null || valor.isBlank()) {
			throw new BusinessRuleException(mensaje);
		}
	}
}
