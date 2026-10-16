package com.prestamos.service;

import com.prestamos.dao.ClienteDAO;
import com.prestamos.exception.BusinessRuleException;
import com.prestamos.exception.DataAccessException;
import com.prestamos.exception.DuplicateResourceException;
import com.prestamos.exception.ResourceNotFoundException;
import com.prestamos.model.cliente.Clientes;
import com.prestamos.model.cliente.Conyuges;
import com.prestamos.model.cliente.ReferenciasPersonales;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Lógica de negocio de la HU "Gestionar Cliente".
 *
 * <p>Reglas cubiertas: RN-01 a RN-06 y criterios CA-02 a CA-07.
 * La unicidad por documento (RN-02/CA-03) y la restricción de
 * desactivación (RN-06/CA-06) se validan también en la BD mediante
 * {@code Cliente.sp_RegistrarCliente}, {@code Cliente.sp_ActualizarCliente}
 * y {@code Cliente.sp_DesactivarCliente}, cuyos códigos de error se
 * traducen a excepciones de negocio en {@link #mapearError}.</p>
 *
 * <p><b>RN-07 pendiente:</b> la verificación de "Analista de Crédito
 * autenticado" requiere conservar la {@code SesionAutenticada} una vez
 * que el login y la interfaz estén conectados (fase de presentación).</p>
 */
@Service
public class ClienteService {

	private static final Pattern CORREO_VALIDO = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
	/** RN-01: DNI, pasaporte o carnet de extranjería, hasta 12 caracteres (NCHAR(12)). */
	private static final Pattern DOCUMENTO_VALIDO = Pattern.compile("^[A-Za-z0-9]{1,12}$");
	/** Hasta 9 caracteres (NCHAR(9)): dígitos, "+" y "-" permitidos. */
	private static final Pattern TELEFONO_VALIDO = Pattern.compile("^[0-9+\\-\\s]{1,9}$");

	/** Límites de las firmas de los procedimientos almacenados. */
	private static final int LONGITUD_CODIGO = 4;
	private static final int LONGITUD_TIPO_DOCUMENTO = 4;
	private static final int LONGITUD_ESTADO_CIVIL = 4;
	private static final int LONGITUD_CONDICION_LABORAL = 4;
	private static final int LONGITUD_NOMBRES_APELLIDOS = 100;
	private static final int LONGITUD_DIRECCION = 150;
	private static final int LONGITUD_CORREO = 100;
	private static final int LONGITUD_OBSERVACIONES = 255;
	private static final int LONGITUD_RELACION_REFERENCIA = 30;

	private final ClienteDAO clienteDAO;

	public ClienteService(ClienteDAO clienteDAO) {
		this.clienteDAO = Objects.requireNonNull(clienteDAO, "clienteDAO es obligatorio");
	}

	/**
	 * CA-02 / CA-03 / CA-05: registra un cliente validando campos
	 * obligatorios (RN-03) y dejando la unicidad del documento (RN-02)
	 * al procedimiento almacenado.
	 *
	 * @return el código asignado al cliente
	 */
	public String registrar(Clientes cliente) {
		preparar(cliente);
		validarDatosCliente(cliente, false);
		try {
			return clienteDAO.insertar(cliente);
		} catch (SQLException exception) {
			throw mapearError(exception, "No se pudo registrar el cliente");
		}
	}

	/**
	 * CA-04 / CA-05 / RN-05: modifica un cliente previamente registrado.
	 */
	public void actualizar(Clientes cliente) {
		preparar(cliente);
		validarDatosCliente(cliente, true);
		obtenerPorCodigo(cliente.getClientesCodigo());
		try {
			clienteDAO.actualizar(cliente);
		} catch (SQLException exception) {
			throw mapearError(exception, "No se pudo actualizar el cliente");
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
			throw mapearError(exception, "No se pudo consultar el cliente");
		}
	}

	/**
	 * CA-01: lista los clientes. {@code Cliente.sp_ListarClientes} no
	 * acepta parámetros, por lo que el filtro de estado se aplica aquí.
	 */
	public List<Clientes> listar(String estado) {
		if (estado != null && !estado.isBlank() && !estado.equals("A") && !estado.equals("I")) {
			throw new BusinessRuleException("El estado del cliente debe ser A o I");
		}
		try {
			List<Clientes> clientes = clienteDAO.listar();
			if (estado == null || estado.isBlank()) {
				return clientes;
			}
			List<Clientes> filtrados = new ArrayList<>();
			for (Clientes cliente : clientes) {
				if (estado.equals(cliente.getClientesEstado())) {
					filtrados.add(cliente);
				}
			}
			return filtrados;
		} catch (SQLException exception) {
			throw mapearError(exception, "No se pudieron listar los clientes");
		}
	}

	/**
	 * CA-06 / RN-06: la restricción de desactivación frente a préstamos
	 * activos la aplica {@code Cliente.sp_DesactivarCliente} en la BD.
	 */
	public void desactivar(String codigo) {
		validarTexto(codigo, "El código del cliente es obligatorio");
		obtenerPorCodigo(codigo);
		try {
			clienteDAO.desactivar(codigo.trim());
		} catch (SQLException exception) {
			throw mapearError(exception, "No se pudo desactivar el cliente");
		}
	}

	/** RN-04: cónyuge (opcional), persistido con DML parametrizado. */
	public void guardarConyuge(Conyuges conyuge) {
		if (conyuge == null) {
			throw new BusinessRuleException("El cónyuge es obligatorio");
		}
		validarTexto(conyuge.getClientesCodigo(), "El cliente del cónyuge es obligatorio");
		validarTexto(conyuge.getConyugesApellidos(), "Los apellidos del cónyuge son obligatorios");
		validarTexto(conyuge.getConyugesNombres(), "Los nombres del cónyuge son obligatorios");
		validarLongitud(conyuge.getConyugesApellidos(), LONGITUD_NOMBRES_APELLIDOS, "Los apellidos del cónyuge");
		validarLongitud(conyuge.getConyugesNombres(), LONGITUD_NOMBRES_APELLIDOS, "Los nombres del cónyuge");
		validarOpcional(conyuge.getConyugesTelefono(), 9, "El teléfono del cónyuge", TELEFONO_VALIDO);
		try {
			clienteDAO.guardarConyuge(conyuge);
		} catch (SQLException exception) {
			throw mapearError(exception, "No se pudo guardar el cónyuge");
		}
	}

	/** RN-04: referencia personal (opcional), persistida con DML parametrizado. */
	public String registrarReferenciaPersonal(ReferenciasPersonales referencia) {
		if (referencia == null) {
			throw new BusinessRuleException("La referencia personal es obligatoria");
		}
		validarTexto(referencia.getClientesCodigo(), "El cliente de la referencia es obligatorio");
		validarTexto(referencia.getReferenciasPersonalesNombres(), "Los nombres de la referencia son obligatorios");
		validarTexto(referencia.getReferenciasPersonalesTelefono(), "El teléfono de la referencia es obligatorio");
		validarTexto(referencia.getReferenciasPersonalesRelacion(), "La relación de la referencia es obligatoria");
		validarLongitud(referencia.getReferenciasPersonalesNombres(), LONGITUD_NOMBRES_APELLIDOS,
				"Los nombres de la referencia");
		validarOpcional(referencia.getReferenciasPersonalesTelefono(), 9, "El teléfono de la referencia",
				TELEFONO_VALIDO);
		validarLongitud(referencia.getReferenciasPersonalesRelacion(), LONGITUD_RELACION_REFERENCIA,
				"La relación de la referencia");
		try {
			return clienteDAO.insertarReferenciaPersonal(referencia);
		} catch (SQLException exception) {
			throw mapearError(exception, "No se pudo registrar la referencia personal");
		}
	}

	// ------------------------------------------------------------------
	// Validaciones (CA-05, RN-01, RN-03)
	// ------------------------------------------------------------------

	private void preparar(Clientes cliente) {
		if (cliente == null) {
			throw new BusinessRuleException("El cliente es obligatorio");
		}
		cliente.setClientesCodigo(normalizar(cliente.getClientesCodigo()));
		cliente.setTiposDocumentoCodigo(normalizar(cliente.getTiposDocumentoCodigo()));
		cliente.setClientesNumeroDocumento(normalizar(cliente.getClientesNumeroDocumento()));
		cliente.setClientesApellidos(normalizar(cliente.getClientesApellidos()));
		cliente.setClientesNombres(normalizar(cliente.getClientesNombres()));
		cliente.setEstadosCivilesCodigo(normalizar(cliente.getEstadosCivilesCodigo()));
		cliente.setClientesDireccion(normalizar(cliente.getClientesDireccion()));
		cliente.setClientesTelefono(normalizar(cliente.getClientesTelefono()));
		cliente.setClientesCorreo(normalizar(cliente.getClientesCorreo()));
		cliente.setCondicionesLaboralesCodigo(normalizar(cliente.getCondicionesLaboralesCodigo()));
		cliente.setClientesObservaciones(normalizar(cliente.getClientesObservaciones()));
	}

	private void validarDatosCliente(Clientes cliente, boolean requiereCodigo) {
		// RN-03: campos obligatorios (CA-05 los agrupa en un solo aviso)
		List<String> faltantes = new ArrayList<>();
		if (requiereCodigo) {
			acumularFaltante(faltantes, cliente.getClientesCodigo(), "código de cliente");
		}
		acumularFaltante(faltantes, cliente.getTiposDocumentoCodigo(), "tipo de documento");
		acumularFaltante(faltantes, cliente.getClientesNumeroDocumento(), "número de documento");
		acumularFaltante(faltantes, cliente.getClientesNombres(), "nombres");
		acumularFaltante(faltantes, cliente.getClientesApellidos(), "apellidos");
		if (cliente.getClientesFechaNacimiento() == null) {
			faltantes.add("fecha de nacimiento");
		}
		acumularFaltante(faltantes, cliente.getEstadosCivilesCodigo(), "estado civil");
		acumularFaltante(faltantes, cliente.getClientesDireccion(), "dirección domiciliaria");
		acumularFaltante(faltantes, cliente.getClientesTelefono(), "teléfono de contacto");
		acumularFaltante(faltantes, cliente.getClientesCorreo(), "correo electrónico");
		acumularFaltante(faltantes, cliente.getCondicionesLaboralesCodigo(), "condición laboral");
		if (cliente.getClientesNivelIngresos() == null) {
			faltantes.add("nivel de ingresos");
		}
		if (!faltantes.isEmpty()) {
			throw new BusinessRuleException(
					"Faltan campos obligatorios del cliente: " + String.join(", ", faltantes));
		}

		// RN-01 y límites de las firmas de los procedimientos almacenados
		if (requiereCodigo) {
			validarLongitud(cliente.getClientesCodigo(), LONGITUD_CODIGO, "El código del cliente");
		}
		validarLongitud(cliente.getTiposDocumentoCodigo(), LONGITUD_TIPO_DOCUMENTO, "El tipo de documento");
		if (!DOCUMENTO_VALIDO.matcher(cliente.getClientesNumeroDocumento()).matches()) {
			throw new BusinessRuleException(
					"El número de documento no tiene un formato válido (letras o dígitos, máximo 12 caracteres)");
		}
		validarLongitud(cliente.getClientesNombres(), LONGITUD_NOMBRES_APELLIDOS, "Los nombres");
		validarLongitud(cliente.getClientesApellidos(), LONGITUD_NOMBRES_APELLIDOS, "Los apellidos");
		if (cliente.getClientesFechaNacimiento().isAfter(LocalDate.now())) {
			throw new BusinessRuleException("La fecha de nacimiento no puede ser futura");
		}
		validarLongitud(cliente.getEstadosCivilesCodigo(), LONGITUD_ESTADO_CIVIL, "El estado civil");
		validarLongitud(cliente.getClientesDireccion(), LONGITUD_DIRECCION, "La dirección domiciliaria");
		if (!TELEFONO_VALIDO.matcher(cliente.getClientesTelefono()).matches()) {
			throw new BusinessRuleException(
					"El teléfono de contacto no tiene un formato válido (máximo 9 caracteres: dígitos, \"+\" o \"-\")");
		}
		if (!CORREO_VALIDO.matcher(cliente.getClientesCorreo()).matches()) {
			throw new BusinessRuleException("El correo electrónico no tiene un formato válido");
		}
		validarLongitud(cliente.getClientesCorreo(), LONGITUD_CORREO, "El correo electrónico");
		validarLongitud(cliente.getCondicionesLaboralesCodigo(), LONGITUD_CONDICION_LABORAL,
				"La condición laboral");
		if (cliente.getClientesNivelIngresos().signum() < 0) {
			throw new BusinessRuleException("El nivel de ingresos no puede ser negativo");
		}
		validarLongitud(cliente.getClientesObservaciones(), LONGITUD_OBSERVACIONES, "Las observaciones");
	}

	private void acumularFaltante(List<String> faltantes, String valor, String etiqueta) {
		if (valor == null || valor.isBlank()) {
			faltantes.add(etiqueta);
		}
	}

	private void validarTexto(String valor, String mensaje) {
		if (valor == null || valor.isBlank()) {
			throw new BusinessRuleException(mensaje);
		}
	}

	private void validarLongitud(String valor, int maximo, String etiqueta) {
		if (valor != null && valor.length() > maximo) {
			throw new BusinessRuleException(
					etiqueta + " no puede superar " + maximo + " caracteres");
		}
	}

	private void validarOpcional(String valor, int maximo, String etiqueta, Pattern formato) {
		if (valor == null || valor.isBlank()) {
			return;
		}
		if (!formato.matcher(valor).matches()) {
			throw new BusinessRuleException(etiqueta + " no tiene un formato válido");
		}
		validarLongitud(valor, maximo, etiqueta);
	}

	private String normalizar(String valor) {
		return valor == null ? null : valor.trim();
	}

	// ------------------------------------------------------------------
	// CA-07: traducción de los códigos de error de la BD
	// ------------------------------------------------------------------

	private RuntimeException mapearError(SQLException excepcion, String operacion) {
		switch (excepcion.getErrorCode()) {
			case 50101:
			case 50111:
				return new BusinessRuleException("Faltan campos obligatorios del cliente (RN-03)");
			case 50102:
				return new DuplicateResourceException("Ya existe un cliente con ese código");
			case 50103:
			case 50114:
				return new DuplicateResourceException(
						"Ya existe un cliente con ese número de documento (RN-02)");
			case 50104:
			case 50115:
				return new BusinessRuleException("El nivel de ingresos no puede ser negativo");
			case 50113:
			case 50121:
				return new ResourceNotFoundException("No existe el cliente solicitado (RN-05)");
			case 50120:
				return new BusinessRuleException("El código del cliente es obligatorio");
			case 50122:
				return new BusinessRuleException(
						"No se puede desactivar el cliente porque tiene préstamos activos, "
								+ "morosos o en incumplimiento (RN-06)");
			case 50123:
				return new BusinessRuleException("El cliente ya se encuentra desactivado");
			case 2601:
			case 2627:
				return mapearDuplicadoPorRestriccion(excepcion, operacion);
			default:
				return new DataAccessException(operacion, excepcion);
		}
	}

	private RuntimeException mapearDuplicadoPorRestriccion(SQLException excepcion, String operacion) {
		String mensaje = excepcion.getMessage();
		String detalle = mensaje == null ? "" : mensaje.toUpperCase(Locale.ROOT);
		if (detalle.contains("CLIENTESDOCUMENTOUQ")) {
			return new DuplicateResourceException("Ya existe un cliente con ese número de documento (RN-02)");
		}
		if (detalle.contains("CLIENTESPK")) {
			return new DuplicateResourceException("Ya existe un cliente con ese código");
		}
		return new DataAccessException(operacion, excepcion);
	}
}
