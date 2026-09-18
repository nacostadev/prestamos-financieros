package com.prestamos.dao;

import com.prestamos.model.cronograma.Cronogramas;
import java.sql.SQLException;

public interface CronogramaDAO {

    String generar(String prestamosCodigo, String analistasCodigo) throws SQLException;

    Cronogramas obtenerPorPrestamo(String prestamosCodigo) throws SQLException;
}