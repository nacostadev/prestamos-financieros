package com.prestamos.dao;

import com.prestamos.model.cliente.Clientes;
import java.util.List;

public interface ClienteDAO {

    List<Clientes> listar();

    Clientes buscarPorId(int id);

    Clientes buscarPorDocumento(String numeroDocumento);

    void guardar(Clientes cliente);

    void actualizar(Clientes cliente);

    void desactivar(int id);
}