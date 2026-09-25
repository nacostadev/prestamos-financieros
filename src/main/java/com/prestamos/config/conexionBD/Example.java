package com.prestamos.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Example {
    private static final String URL = "jdbc:sqlserver://SERVIDOR\\INSTANCIA:PUERTO;databaseName=BASE_DE_DATOS;integratedSecurity=true;encrypt=true;trustServerCertificate=true";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}