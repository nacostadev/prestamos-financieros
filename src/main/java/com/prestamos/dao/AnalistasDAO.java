package com.prestamos.dao;

import com.prestamos.model.personal.Analistas;
import java.sql.SQLException;
import java.util.List;

public interface AnalistasDAO {

    Analistas obtenerPorCodigo(String analistasCodigo) throws SQLException;

    List<Analistas> listar() throws SQLException;

    List<Analistas> listarActivos() throws SQLException;
}