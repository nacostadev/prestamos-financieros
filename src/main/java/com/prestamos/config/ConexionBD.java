package com.prestamos.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:sqlserver://DESKTOP-17KJMAR\\SQL2025:1433;databaseName=BDGestionCliente;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
