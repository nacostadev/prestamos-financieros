package com.prestamos.config;

import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        try (Connection conn = ConexionBD.getConexion()) {
            if (conn != null) {
                System.out.println("✅ Conexión exitosa a SQL Server");
            } else {
                System.out.println("❌ No se pudo establecer la conexión");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error al conectar con la base de datos:");
            e.printStackTrace();
        }
    }
}