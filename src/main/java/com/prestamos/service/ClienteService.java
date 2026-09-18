package com.prestamos.service;

import com.prestamos.dao.ClienteDAO;
import com.prestamos.exception.DataAccessException;
import com.prestamos.exception.DuplicateResourceException;
import com.prestamos.exception.ResourceNotFoundException;
import com.prestamos.exception.BusinessRuleException;
import com.prestamos.model.cliente.Clientes;
import com.prestamos.model.cliente.Conyuges;
import com.prestamos.model.cliente.ReferenciasPersonales;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class ClienteService {

	private static final Pattern CORREO_VALIDO = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
	private final ClienteDAO clienteDAO;

	public ClienteService(ClienteDAO clienteDAO) {
		this.clienteDAO = Objects.requireNonNull(clienteDAO, "clienteDAO es obligatorio");
	}

	public String registrar(Clientes cliente) {
		validarDatosCliente(cliente, false);
		validarDocumentoDuplicado(cliente, null);
		try {
			return clienteDAO.insertar(cliente);
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudo registrar el cliente", exception);
		}
	}

	public void actualizar(Clientes cliente) {
		validarDatosCliente(cliente, true);
		validarDocumentoDuplicado(cliente, cliente.getClientesCodigo());
		obtenerPorCodigo(cliente.getClientesCodigo());
		try {
			clienteDAO.actualizar(cliente);
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudo actualizar el cliente", exception);
		}
	}

	public Clientes obtenerPorCodigo(String codigo) {
		validarTexto(codigo, "El código del cliente es obligatorio");
		try {
			Clientes cliente = clienteDAO.obtenerPorCodigo(codigo.trim());
			if (cliente == null) {
				throw new ResourceNotFoundException("No existe el cliente solicitado");
			}
			return cliente;
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudo consultar el cliente", exception);
		}
	}

	public List<Clientes> listar(String estado) {
		if (estado != null && !estado.isBlank() && !estado.equals("A") && !estado.equals("I")) {
			throw new BusinessRuleException("El estado del cliente debe ser A o I");
		}
		try {
			return clienteDAO.listar(estado);
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudo listar los clientes", exception);
		}
	}

	public void desactivar(String codigo, String analistaCodigo, String motivo) {
		obtenerPorCodigo(codigo);
		validarTexto(analistaCodigo, "El analista que desactiva es obligatorio");
		validarTexto(motivo, "El motivo de desactivación es obligatorio");
		try {
			clienteDAO.desactivar(codigo.trim(), analistaCodigo.trim(), motivo.trim());
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudo desactivar el cliente", exception);
		}
	}

	public void guardarConyuge(Conyuges conyuge) {
		if (conyuge == null) {
			throw new BusinessRuleException("El cónyuge es obligatorio");
		}
		validarTexto(conyuge.getClientesCodigo(), "El cliente del cónyuge es obligatorio");
		try {
			clienteDAO.guardarConyuge(conyuge);
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudo guardar el cónyuge", exception);
		}
	}

	public String registrarReferenciaPersonal(ReferenciasPersonales referencia) {
		if (referencia == null) {
			throw new BusinessRuleException("La referencia personal es obligatoria");
		}
		validarTexto(referencia.getClientesCodigo(), "El cliente de la referencia es obligatorio");
		try {
			return clienteDAO.insertarReferenciaPersonal(referencia);
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudo registrar la referencia personal", exception);
		}
	}

	public void desactivarReferenciaPersonal(String codigo) {
		validarTexto(codigo, "El código de la referencia es obligatorio");
		try {
			clienteDAO.desactivarReferenciaPersonal(codigo.trim());
		} catch (SQLException exception) {
			throw new DataAccessException("No se pudo desactivar la referencia personal", exception);
		}
	}

	private void validarDatosCliente(Clientes cliente, boolean requiereCodigo) {
		if (cliente == null) {
			throw new BusinessRuleException("El cliente es obligatorio");
		}
		if (requiereCodigo) {
			validarTexto(cliente.getClientesCodigo(), "El código del cliente es obligatorio");
		}
		validarTexto(cliente.getTiposDocumentoCodigo(), "El tipo de documento es obligatorio");
		validarTexto(cliente.getClientesNumeroDocumento(), "El número de documento es obligatorio");
		validarTexto(cliente.getClientesPaterno(), "El apellido paterno es obligatorio");
		validarTexto(cliente.getClientesNombres(), "Los nombres son obligatorios");
		if (cliente.getClientesFechaNacimiento() == null) {
			throw new BusinessRuleException("La fecha de nacimiento es obligatoria");
		}
		if (cliente.getClientesCorreo() == null || !CORREO_VALIDO.matcher(cliente.getClientesCorreo().trim()).matches()) {
			throw new BusinessRuleException("El correo del cliente no tiene un formato válido");
		}
		if (cliente.getClientesNivelIngresos() != null && cliente.getClientesNivelIngresos().signum() < 0) {
			throw new BusinessRuleException("El nivel de ingresos no puede ser negativo");
		}
	}

	private void validarDocumentoDuplicado(Clientes cliente, String codigoActual) {
		List<Clientes> clientes = listar(null);
		if (clientes == null) {
			return;
		}
		for (Clientes existente : clientes) {
			if (existente == null) {
				continue;
			}
			boolean mismoDocumento = cliente.getTiposDocumentoCodigo().trim().equals(existente.getTiposDocumentoCodigo())
					&& cliente.getClientesNumeroDocumento().trim().equals(existente.getClientesNumeroDocumento());
			boolean esOtroCliente = codigoActual == null || !codigoActual.trim().equals(existente.getClientesCodigo());
			if (mismoDocumento && esOtroCliente) {
				throw new DuplicateResourceException("Ya existe un cliente con el mismo documento");
			}
		}
	}

	private void validarTexto(String valor, String mensaje) {
		if (valor == null || valor.isBlank()) {
			throw new BusinessRuleException(mensaje);
		}
	}
}
// hola estoy probando el proyect