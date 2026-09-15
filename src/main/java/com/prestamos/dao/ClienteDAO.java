package com.prestamos.dao;

import com.prestamos.model.cliente.Cliente;
import java.util.List;

public interface ClienteDAO {

    List<Cliente> listar();

    Cliente buscarPorId(int id);

    Cliente buscarPorDocumento(String numeroDocumento);

    void guardar(Cliente cliente);

    void actualizar(Cliente cliente);

    void desactivar(int id);
}