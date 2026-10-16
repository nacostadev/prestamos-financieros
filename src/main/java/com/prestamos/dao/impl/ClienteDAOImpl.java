package com.prestamos.dao.impl;

import com.prestamos.config.ConexionBD;
import com.prestamos.dao.ClienteDAO;
import com.prestamos.exception.BusinessRuleException;
import com.prestamos.model.cliente.Clientes;
import com.prestamos.model.cliente.Conyuges;
import com.prestamos.model.cliente.ReferenciasPersonales;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Integración de la HU "Gestionar Cliente" con los procedimientos
 * almacenados del esquema Cliente:
 *
 * <ul>
 *   <li>Cliente.sp_ListarClientes</li>
 *   <li>Cliente.sp_RegistrarCliente</li>
 *   <li>Cliente.sp_ActualizarCliente</li>
 *   <li>Cliente.sp_DesactivarCliente</li>
 * </ul>
 *
 * Cónyuge y referencias personales (RN-04) se persisten con DML
 * parametrizado porque no disponen de procedimientos almacenados.
 */
public class ClienteDAOImpl implements ClienteDAO {

    private static final String SP_REGISTRAR_CLIENTE = "{call Cliente.sp_RegistrarCliente(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    private static final String SP_ACTUALIZAR_CLIENTE = "{call Cliente.sp_ActualizarCliente(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    private static final String SP_DESACTIVAR_CLIENTE = "{call Cliente.sp_DesactivarCliente(?)}";
    private static final String SP_LISTAR_CLIENTES = "{call Cliente.sp_ListarClientes}";

    private static final String SQL_MAXIMO_CLIENTE = "SELECT MAX(ClientesCodigo) FROM Cliente.Clientes";
    private static final String SQL_MAXIMO_CONYUGE = "SELECT MAX(ConyugesCodigo) FROM Cliente.Conyuges";
    private static final String SQL_MAXIMO_REFERENCIA =
            "SELECT MAX(ReferenciasPersonalesCodigo) FROM Cliente.ReferenciasPersonales";

    private static final String SQL_CONSULTAR_CLIENTE =
            "SELECT c.ClientesCodigo, c.TiposDocumentoCodigo, td.TiposDocumentoNombre, "
                    + "c.ClientesNumeroDocumento, c.ClientesApellidos, c.ClientesNombres, "
                    + "c.ClientesFechaNacimiento, c.EstadosCivilesCodigo, ec.EstadosCivilesNombre, "
                    + "c.ClientesDireccion, c.ClientesTelefono, c.ClientesCorreo, "
                    + "c.CondicionesLaboralesCodigo, cl.CondicionesLaboralesNombre, "
                    + "c.ClientesNivelIngresos, c.ClientesObservaciones, c.ClientesEstado "
                    + "FROM Cliente.Clientes AS c "
                    + "INNER JOIN Cliente.TiposDocumento AS td ON td.TiposDocumentoCodigo = c.TiposDocumentoCodigo "
                    + "INNER JOIN Cliente.EstadosCiviles AS ec ON ec.EstadosCivilesCodigo = c.EstadosCivilesCodigo "
                    + "INNER JOIN Cliente.CondicionesLaborales AS cl "
                    + "ON cl.CondicionesLaboralesCodigo = c.CondicionesLaboralesCodigo "
                    + "WHERE c.ClientesCodigo = ?";

    private static final String SQL_CONSULTAR_CONYUGE =
            "SELECT ConyugesCodigo, ClientesCodigo, ConyugesApellidos, ConyugesNombres, ConyugesTelefono "
                    + "FROM Cliente.Conyuges WHERE ClientesCodigo = ?";

    private static final String SQL_CONSULTAR_REFERENCIAS =
            "SELECT ReferenciasPersonalesCodigo, ClientesCodigo, ReferenciasPersonalesNombres, "
                    + "ReferenciasPersonalesTelefono, ReferenciasPersonalesRelacion "
                    + "FROM Cliente.ReferenciasPersonales WHERE ClientesCodigo = ? "
                    + "ORDER BY ReferenciasPersonalesCodigo";

    private static final String SQL_CONYUGE_EXISTE =
            "SELECT 1 FROM Cliente.Conyuges WHERE ClientesCodigo = ?";
    private static final String SQL_CONYUGE_ACTUALIZAR =
            "UPDATE Cliente.Conyuges SET ConyugesApellidos = ?, ConyugesNombres = ?, ConyugesTelefono = ? "
                    + "WHERE ClientesCodigo = ?";
    private static final String SQL_CONYUGE_INSERTAR =
            "INSERT INTO Cliente.Conyuges (ConyugesCodigo, ClientesCodigo, ConyugesApellidos, "
                    + "ConyugesNombres, ConyugesTelefono) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_REFERENCIA_INSERTAR =
            "INSERT INTO Cliente.ReferenciasPersonales (ReferenciasPersonalesCodigo, ClientesCodigo, "
                    + "ReferenciasPersonalesNombres, ReferenciasPersonalesTelefono, "
                    + "ReferenciasPersonalesRelacion) VALUES (?, ?, ?, ?, ?)";

    private static final String CODIGO_CLIENTE_INICIAL = "CL01";
    private static final String CODIGO_CONYUGE_INICIAL = "CO01";
    private static final String CODIGO_REFERENCIA_INICIAL = "REF001";

    // ------------------------------------------------------------------
    // Cliente.sp_RegistrarCliente
    // ------------------------------------------------------------------
    @Override
    public String insertar(Clientes cliente) throws SQLException {
        if (estaVacio(cliente.getClientesCodigo())) {
            cliente.setClientesCodigo(siguienteCodigo(SQL_MAXIMO_CLIENTE, CODIGO_CLIENTE_INICIAL, 4));
        } else {
            cliente.setClientesCodigo(cliente.getClientesCodigo().trim());
        }

        try (Connection cn = ConexionBD.getConexion();
             CallableStatement cs = cn.prepareCall(SP_REGISTRAR_CLIENTE)) {

            cs.setString(1, cliente.getClientesCodigo());
            cs.setString(2, cliente.getTiposDocumentoCodigo());
            cs.setString(3, cliente.getClientesNumeroDocumento());
            cs.setString(4, cliente.getClientesApellidos());
            cs.setString(5, cliente.getClientesNombres());
            cs.setDate(6, java.sql.Date.valueOf(cliente.getClientesFechaNacimiento()));
            cs.setString(7, cliente.getEstadosCivilesCodigo());
            cs.setString(8, cliente.getClientesDireccion());
            cs.setString(9, cliente.getClientesTelefono());
            cs.setString(10, cliente.getClientesCorreo());
            cs.setString(11, cliente.getCondicionesLaboralesCodigo());
            cs.setBigDecimal(12, cliente.getClientesNivelIngresos());
            cs.setString(13, cliente.getClientesObservaciones());

            cs.execute();
        }
        return cliente.getClientesCodigo();
    }

    // ------------------------------------------------------------------
    // Cliente.sp_ActualizarCliente
    // ------------------------------------------------------------------
    @Override
    public void actualizar(Clientes cliente) throws SQLException {
        try (Connection cn = ConexionBD.getConexion();
             CallableStatement cs = cn.prepareCall(SP_ACTUALIZAR_CLIENTE)) {

            cs.setString(1, cliente.getClientesCodigo());
            cs.setString(2, cliente.getTiposDocumentoCodigo());
            cs.setString(3, cliente.getClientesNumeroDocumento());
            cs.setString(4, cliente.getClientesApellidos());
            cs.setString(5, cliente.getClientesNombres());
            cs.setDate(6, java.sql.Date.valueOf(cliente.getClientesFechaNacimiento()));
            cs.setString(7, cliente.getEstadosCivilesCodigo());
            cs.setString(8, cliente.getClientesDireccion());
            cs.setString(9, cliente.getClientesTelefono());
            cs.setString(10, cliente.getClientesCorreo());
            cs.setString(11, cliente.getCondicionesLaboralesCodigo());
            cs.setBigDecimal(12, cliente.getClientesNivelIngresos());
            cs.setString(13, cliente.getClientesObservaciones());

            cs.execute();
        }
    }

    // ------------------------------------------------------------------
    // Cliente.sp_DesactivarCliente (aplica RN-06 dentro de la BD)
    // ------------------------------------------------------------------
    @Override
    public void desactivar(String clientesCodigo) throws SQLException {
        try (Connection cn = ConexionBD.getConexion();
             CallableStatement cs = cn.prepareCall(SP_DESACTIVAR_CLIENTE)) {

            cs.setString(1, clientesCodigo);
            cs.execute();
        }
    }

    // ------------------------------------------------------------------
    // Cliente.sp_ListarClientes
    // ------------------------------------------------------------------
    @Override
    public List<Clientes> listar() throws SQLException {
        List<Clientes> lista = new ArrayList<>();

        try (Connection cn = ConexionBD.getConexion();
             CallableStatement cs = cn.prepareCall(SP_LISTAR_CLIENTES);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearCliente(rs));
            }
        }
        return lista;
    }

    // ------------------------------------------------------------------
    // Consulta puntual + cónyuge + referencias (DML parametrizado)
    // ------------------------------------------------------------------
    @Override
    public Clientes obtenerPorCodigo(String clientesCodigo) throws SQLException {
        try (Connection cn = ConexionBD.getConexion()) {
            Clientes cliente = null;

            try (PreparedStatement ps = cn.prepareStatement(SQL_CONSULTAR_CLIENTE)) {
                ps.setString(1, clientesCodigo);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        cliente = mapearCliente(rs);
                    }
                }
            }
            if (cliente == null) {
                return null;
            }

            try (PreparedStatement ps = cn.prepareStatement(SQL_CONSULTAR_CONYUGE)) {
                ps.setString(1, clientesCodigo);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Conyuges c = new Conyuges();
                        c.setConyugesCodigo(rs.getString("ConyugesCodigo"));
                        c.setClientesCodigo(rs.getString("ClientesCodigo"));
                        c.setConyugesApellidos(rs.getString("ConyugesApellidos"));
                        c.setConyugesNombres(rs.getString("ConyugesNombres"));
                        c.setConyugesTelefono(rs.getString("ConyugesTelefono"));
                        cliente.setConyuge(c);
                    }
                }
            }

            try (PreparedStatement ps = cn.prepareStatement(SQL_CONSULTAR_REFERENCIAS)) {
                ps.setString(1, clientesCodigo);
                List<ReferenciasPersonales> referencias = new ArrayList<>();
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        ReferenciasPersonales r = new ReferenciasPersonales();
                        r.setReferenciasPersonalesCodigo(rs.getString("ReferenciasPersonalesCodigo"));
                        r.setClientesCodigo(rs.getString("ClientesCodigo"));
                        r.setReferenciasPersonalesNombres(rs.getString("ReferenciasPersonalesNombres"));
                        r.setReferenciasPersonalesTelefono(rs.getString("ReferenciasPersonalesTelefono"));
                        r.setReferenciasPersonalesRelacion(rs.getString("ReferenciasPersonalesRelacion"));
                        referencias.add(r);
                    }
                }
                cliente.setReferencias(referencias);
            }

            return cliente;
        }
    }

    @Override
    public void guardarConyuge(Conyuges conyuge) throws SQLException {
        try (Connection cn = ConexionBD.getConexion()) {
            boolean existe;
            try (PreparedStatement ps = cn.prepareStatement(SQL_CONYUGE_EXISTE)) {
                ps.setString(1, conyuge.getClientesCodigo());
                try (ResultSet rs = ps.executeQuery()) {
                    existe = rs.next();
                }
            }

            if (existe) {
                try (PreparedStatement ps = cn.prepareStatement(SQL_CONYUGE_ACTUALIZAR)) {
                    ps.setString(1, conyuge.getConyugesApellidos());
                    ps.setString(2, conyuge.getConyugesNombres());
                    ps.setString(3, conyuge.getConyugesTelefono());
                    ps.setString(4, conyuge.getClientesCodigo());
                    ps.executeUpdate();
                }
            } else {
                if (estaVacio(conyuge.getConyugesCodigo())) {
                    conyuge.setConyugesCodigo(
                            siguienteCodigo(SQL_MAXIMO_CONYUGE, CODIGO_CONYUGE_INICIAL, 4));
                }
                try (PreparedStatement ps = cn.prepareStatement(SQL_CONYUGE_INSERTAR)) {
                    ps.setString(1, conyuge.getConyugesCodigo().trim());
                    ps.setString(2, conyuge.getClientesCodigo());
                    ps.setString(3, conyuge.getConyugesApellidos());
                    ps.setString(4, conyuge.getConyugesNombres());
                    ps.setString(5, conyuge.getConyugesTelefono());
                    ps.executeUpdate();
                }
            }
        }
    }

    @Override
    public String insertarReferenciaPersonal(ReferenciasPersonales referencia) throws SQLException {
        if (estaVacio(referencia.getReferenciasPersonalesCodigo())) {
            referencia.setReferenciasPersonalesCodigo(
                    siguienteCodigo(SQL_MAXIMO_REFERENCIA, CODIGO_REFERENCIA_INICIAL, 6));
        }

        try (Connection cn = ConexionBD.getConexion();
             PreparedStatement ps = cn.prepareStatement(SQL_REFERENCIA_INSERTAR)) {

            ps.setString(1, referencia.getReferenciasPersonalesCodigo().trim());
            ps.setString(2, referencia.getClientesCodigo());
            ps.setString(3, referencia.getReferenciasPersonalesNombres());
            ps.setString(4, referencia.getReferenciasPersonalesTelefono());
            ps.setString(5, referencia.getReferenciasPersonalesRelacion());
            ps.executeUpdate();
        }
        return referencia.getReferenciasPersonalesCodigo();
    }

    // ------------------------------------------------------------------
    // Utilidades
    // ------------------------------------------------------------------

    /**
     * Genera el siguiente código con la secuencia MAX+1 respetando el
     * prefijo y el ancho del último código emitido (p. ej. CL01 -&gt; CL02).
     */
    private String siguienteCodigo(String sqlMaximo, String codigoInicial, int longitudMaxima) throws SQLException {
        String maximo = null;

        try (Connection cn = ConexionBD.getConexion();
             PreparedStatement ps = cn.prepareStatement(sqlMaximo);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                maximo = rs.getString(1);
            }
        }

        maximo = maximo == null ? "" : maximo.trim();
        if (maximo.isEmpty()) {
            return codigoInicial;
        }

        int finPrefijo = maximo.length();
        while (finPrefijo > 0 && Character.isDigit(maximo.charAt(finPrefijo - 1))) {
            finPrefijo--;
        }

        String prefijo = maximo.substring(0, finPrefijo);
        String digitos = maximo.substring(finPrefijo);

        if (digitos.isEmpty()) {
            throw new BusinessRuleException(
                    "No se puede generar el siguiente código: el último código '" + maximo
                            + "' no tiene sufijo numérico.");
        }

        String siguiente = prefijo + String.format("%0" + digitos.length() + "d",
                Long.parseLong(digitos) + 1);

        if (siguiente.length() > longitudMaxima) {
            throw new BusinessRuleException(
                    "Se alcanzó el límite de códigos disponibles (" + maximo + ").");
        }
        return siguiente;
    }

    private boolean estaVacio(String valor) {
        return valor == null || valor.isBlank();
    }

    private Clientes mapearCliente(ResultSet rs) throws SQLException {
        Clientes c = new Clientes();
        c.setClientesCodigo(rs.getString("ClientesCodigo"));
        c.setTiposDocumentoCodigo(rs.getString("TiposDocumentoCodigo"));
        c.setTiposDocumentoNombre(rs.getString("TiposDocumentoNombre"));
        c.setClientesNumeroDocumento(rs.getString("ClientesNumeroDocumento"));
        c.setClientesApellidos(rs.getString("ClientesApellidos"));
        c.setClientesNombres(rs.getString("ClientesNombres"));
        c.setClientesFechaNacimiento(rs.getDate("ClientesFechaNacimiento").toLocalDate());
        c.setEstadosCivilesCodigo(rs.getString("EstadosCivilesCodigo"));
        c.setEstadosCivilesNombre(rs.getString("EstadosCivilesNombre"));
        c.setClientesDireccion(rs.getString("ClientesDireccion"));
        c.setClientesTelefono(rs.getString("ClientesTelefono"));
        c.setClientesCorreo(rs.getString("ClientesCorreo"));
        c.setCondicionesLaboralesCodigo(rs.getString("CondicionesLaboralesCodigo"));
        c.setCondicionesLaboralesNombre(rs.getString("CondicionesLaboralesNombre"));
        c.setClientesNivelIngresos(rs.getBigDecimal("ClientesNivelIngresos"));
        c.setClientesObservaciones(rs.getString("ClientesObservaciones"));
        c.setClientesEstado(rs.getString("ClientesEstado"));
        return c;
    }
}
