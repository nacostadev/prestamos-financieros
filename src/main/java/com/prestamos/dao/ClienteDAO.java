package com.prestamos.dao;

import com.prestamos.model.cliente.Clientes;
import com.prestamos.model.cliente.Conyuges;
import com.prestamos.model.cliente.ReferenciasPersonales;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {

    /**
     * Registra un cliente mediante Cliente.sp_RegistrarCliente.
     * Si el código no viene, se genera con la secuencia MAX+1.
     *
     * @return el código asignado al cliente
     */
    String insertar(Clientes cliente) throws SQLException;

    /**
     * Modifica un cliente registrado mediante Cliente.sp_ActualizarCliente.
     */
    void actualizar(Clientes cliente) throws SQLException;

    /**
     * Desactiva un cliente mediante Cliente.sp_DesactivarCliente.
     * El propio procedimiento aplica la RN-06 (préstamos activos).
     */
    void desactivar(String clientesCodigo) throws SQLException;

    /**
     * Listado completo mediante Cliente.sp_ListarClientes.
     * El filtro de estado se aplica en la capa de servicio.
     */
    List<Clientes> listar() throws SQLException;

    /**
     * Consulta un cliente por código (incluye cónyuge y referencias personales).
     *
     * @return el cliente o {@code null} si no existe
     */
    Clientes obtenerPorCodigo(String clientesCodigo) throws SQLException;

    /**
     * Inserta o actualiza el cónyuge del cliente (RN-04, opcional).
     */
    void guardarConyuge(Conyuges conyuge) throws SQLException;

    /**
     * Registra una referencia personal del cliente (RN-04, opcional).
     *
     * @return el código asignado a la referencia
     */
    String insertarReferenciaPersonal(ReferenciasPersonales referencia) throws SQLException;
}
