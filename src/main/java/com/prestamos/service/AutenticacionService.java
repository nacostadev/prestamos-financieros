package com.prestamos.service;

import com.prestamos.dao.UsuarioRolDAO;
import com.prestamos.dao.UsuariosDAO;
import com.prestamos.exception.BadCredentialsException;
import com.prestamos.exception.BusinessRuleException;
import com.prestamos.exception.DataAccessException;
import com.prestamos.exception.ResourceNotFoundException;
import com.prestamos.exception.UserDisabledException;
import com.prestamos.model.seguridad.Roles;
import com.prestamos.model.seguridad.SesionAutenticada;
import com.prestamos.model.seguridad.Usuarios;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class AutenticacionService {

	private final UsuariosDAO usuariosDAO;
	private final UsuarioRolDAO usuarioRolDAO;

	public AutenticacionService(UsuariosDAO usuariosDAO, UsuarioRolDAO usuarioRolDAO) {
		this.usuariosDAO = Objects.requireNonNull(usuariosDAO, "usuariosDAO es obligatorio");
		this.usuarioRolDAO = Objects.requireNonNull(usuarioRolDAO, "usuarioRolDAO es obligatorio");
	}

	public SesionAutenticada login(String username, String password) {
		validarEntrada(username, password);
		Usuarios usuario = buscarUsuario(username);
		validarEstadoActivo(usuario);
		validarCredenciales(username, password);
		try {
			List<Roles> roles = usuarioRolDAO.obtenerRolesPorUsuarioId(usuario.getUsuariosCodigo());
			return new SesionAutenticada(usuario.getUsuariosCodigo(), usuario.getUsuariosNombre(),
					usuario.getAnalistasCodigo(), roles == null ? List.of() : roles);
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudieron cargar los roles del usuario", exception);
		}
	}

	private Usuarios buscarUsuario(String username) {
		try {
			Usuarios usuario = usuariosDAO.buscarPorUsername(username.trim());
			if (usuario == null) {
				throw new ResourceNotFoundException("No existe el usuario solicitado");
			}
			return usuario;
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudo consultar el usuario", exception);
		}
	}

	private void validarEstadoActivo(Usuarios usuario) {
		if (!"A".equalsIgnoreCase(usuario.getUsuariosEstado())) {
			throw new UserDisabledException();
		}
	}

	private void validarCredenciales(String username, String password) {
		try {
			if (!usuariosDAO.validarCredenciales(username.trim(), password)) {
				throw new BadCredentialsException();
			}
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudieron validar las credenciales", exception);
		}
	}

	private void validarEntrada(String username, String password) {
		if (username == null || username.isBlank()) {
			throw new BusinessRuleException("El username es obligatorio");
		}
		if (password == null || password.isBlank()) {
			throw new BusinessRuleException("La contraseña es obligatoria");
		}
	}
}