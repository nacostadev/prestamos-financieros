package com.prestamos.dao.impl;

import com.prestamos.config.ConexionBD;
import com.prestamos.dao.ClienteDAO;
import com.prestamos.model.cliente.Clientes;
import com.prestamos.model.cliente.Conyuges;
import com.prestamos.model.cliente.ReferenciasPersonales;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public String insertar(Clientes cliente) throws SQLException {
        String sql = "{call Cliente.sp_Cliente_Insertar(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

        try (Connection cn = ConexionBD.getConexion();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, cliente.getTiposDocumentoCodigo());
            cs.setString(2, cliente.getClientesNumeroDocumento());
            cs.setString(3, cliente.getClientesPaterno());
            cs.setString(4, cliente.getClientesMaterno());
            cs.setString(5, cliente.getClientesNombres());
            cs.setDate(6, java.sql.Date.valueOf(cliente.getClientesFechaNacimiento()));
            cs.setString(7, cliente.getEstadosCivilesCodigo());
            cs.setString(8, cliente.getClientesDireccion());
            cs.setString(9, cliente.getClientesTelefono());
            cs.setString(10, cliente.getClientesCorreo());
            cs.setString(11, cliente.getCondicionesLaboralesCodigo());
            cs.setBigDecimal(12, cliente.getClientesNivelIngresos());
            cs.setString(13, cliente.getClientesObservaciones());
            cs.setString(14, cliente.getAnalistasCodigoRegistro());
            cs.registerOutParameter(15, Types.CHAR);

            cs.execute();
            return cs.getString(15);
        }
    }

    @Override
    public void actualizar(Clientes cliente) throws SQLException {
        String sql = "{call Cliente.sp_Cliente_Actualizar(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

        try (Connection cn = ConexionBD.getConexion();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, cliente.getClientesCodigo());
            cs.setString(2, cliente.getTiposDocumentoCodigo());
            cs.setString(3, cliente.getClientesNumeroDocumento());
            cs.setString(4, cliente.getClientesPaterno());
            cs.setString(5, cliente.getClientesMaterno());
            cs.setString(6, cliente.getClientesNombres());
            cs.setDate(7, java.sql.Date.valueOf(cliente.getClientesFechaNacimiento()));
            cs.setString(8, cliente.getEstadosCivilesCodigo());
            cs.setString(9, cliente.getClientesDireccion());
            cs.setString(10, cliente.getClientesTelefono());
            cs.setString(11, cliente.getClientesCorreo());
            cs.setString(12, cliente.getCondicionesLaboralesCodigo());
            cs.setBigDecimal(13, cliente.getClientesNivelIngresos());
            cs.setString(14, cliente.getClientesObservaciones());
            cs.setString(15, cliente.getAnalistasCodigoModifica());

            cs.execute();
        }
    }

    @Override
    public void desactivar(String clientesCodigo, String analistasCodigo, String motivo) throws SQLException {
        String sql = "{call Cliente.sp_Cliente_Desactivar(?,?,?)}";

        try (Connection cn = ConexionBD.getConexion();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, clientesCodigo);
            cs.setString(2, analistasCodigo);
            cs.setString(3, motivo);

            cs.execute();
        }
    }

    @Override
    public List<Clientes> listar(String estado) throws SQLException {
        List<Clientes> lista = new ArrayList<>();
        String sql = "{call Cliente.sp_Cliente_Listar(?)}";

        try (Connection cn = ConexionBD.getConexion();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, estado);

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearClienteBasico(rs));
                }
            }
        }
        return lista;
    }

    @Override
    public Clientes obtenerPorCodigo(String clientesCodigo) throws SQLException {
        String sql = "{call Cliente.sp_Cliente_ObtenerPorCodigo(?)}";

        try (Connection cn = ConexionBD.getConexion();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, clientesCodigo);

            boolean hayResultados = cs.execute();
            Clientes cliente = null;

            if (hayResultados) {
                try (ResultSet rs = cs.getResultSet()) {
                    if (rs.next()) {
                        cliente = mapearClienteBasico(rs);
                    }
                }
            }
            if (cliente == null) {
                return null;
            }

            if (cs.getMoreResults()) {
                try (ResultSet rs = cs.getResultSet()) {
                    if (rs.next()) {
                        Conyuges c = new Conyuges();
                        c.setConyugesCodigo(rs.getString("ConyugesCodigo"));
                        c.setConyugesPaterno(rs.getString("ConyugesPaterno"));
                        c.setConyugesMaterno(rs.getString("ConyugesMaterno"));
                        c.setConyugesNombres(rs.getString("ConyugesNombres"));
                        c.setConyugesTelefono(rs.getString("ConyugesTelefono"));
                        cliente.setConyuge(c);
                    }
                }
            }

            if (cs.getMoreResults()) {
                List<ReferenciasPersonales> referencias = new ArrayList<>();
                try (ResultSet rs = cs.getResultSet()) {
                    while (rs.next()) {
                        ReferenciasPersonales r = new ReferenciasPersonales();
                        r.setReferenciasPersonalesCodigo(rs.getString("ReferenciasPersonalesCodigo"));
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
        String sql = "{call Cliente.sp_Conyuge_Guardar(?,?,?,?,?)}";

        try (Connection cn = ConexionBD.getConexion();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, conyuge.getClientesCodigo());
            cs.setString(2, conyuge.getConyugesPaterno());
            cs.setString(3, conyuge.getConyugesMaterno());
            cs.setString(4, conyuge.getConyugesNombres());
            cs.setString(5, conyuge.getConyugesTelefono());

            cs.execute();
        }
    }

    @Override
    public String insertarReferenciaPersonal(ReferenciasPersonales referencia) throws SQLException {
        String sql = "{call Cliente.sp_ReferenciaPersonal_Insertar(?,?,?,?,?)}";

        try (Connection cn = ConexionBD.getConexion();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, referencia.getClientesCodigo());
            cs.setString(2, referencia.getReferenciasPersonalesNombres());
            cs.setString(3, referencia.getReferenciasPersonalesTelefono());
            cs.setString(4, referencia.getReferenciasPersonalesRelacion());
            cs.registerOutParameter(5, Types.CHAR);

            cs.execute();
            return cs.getString(5);
        }
    }

    @Override
    public void desactivarReferenciaPersonal(String referenciasPersonalesCodigo) throws SQLException {
        String sql = "{call Cliente.sp_ReferenciaPersonal_Desactivar(?)}";

        try (Connection cn = ConexionBD.getConexion();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, referenciasPersonalesCodigo);
            cs.execute();
        }
    }

    private Clientes mapearClienteBasico(ResultSet rs) throws SQLException {
        Clientes c = new Clientes();
        c.setClientesCodigo(rs.getString("ClientesCodigo"));
        c.setTiposDocumentoCodigo(rs.getString("TiposDocumentoCodigo"));
        c.setClientesNumeroDocumento(rs.getString("ClientesNumeroDocumento"));
        c.setClientesPaterno(rs.getString("ClientesPaterno"));
        c.setClientesMaterno(rs.getString("ClientesMaterno"));
        c.setClientesNombres(rs.getString("ClientesNombres"));
        c.setClientesFechaNacimiento(rs.getDate("ClientesFechaNacimiento").toLocalDate());
        c.setEstadosCivilesCodigo(rs.getString("EstadosCivilesCodigo"));
        c.setClientesDireccion(rs.getString("ClientesDireccion"));
        c.setClientesTelefono(rs.getString("ClientesTelefono"));
        c.setClientesCorreo(rs.getString("ClientesCorreo"));
        c.setCondicionesLaboralesCodigo(rs.getString("CondicionesLaboralesCodigo"));
        c.setClientesNivelIngresos(rs.getBigDecimal("ClientesNivelIngresos"));
        c.setClientesObservaciones(rs.getString("ClientesObservaciones"));
        c.setClientesEstado(rs.getString("ClientesEstado"));
        c.setAnalistasCodigoRegistro(rs.getString("AnalistasCodigoRegistro"));
        c.setAnalistasCodigoModifica(rs.getString("AnalistasCodigoModifica"));

        Timestamp tsRegistro = rs.getTimestamp("ClientesFechaRegistro");
        c.setClientesFechaRegistro(tsRegistro != null ? tsRegistro.toLocalDateTime() : null);

        Timestamp tsActualizacion = rs.getTimestamp("ClientesFechaActualizacion");
        c.setClientesFechaActualizacion(tsActualizacion != null ? tsActualizacion.toLocalDateTime() : null);

        return c;
    }
}