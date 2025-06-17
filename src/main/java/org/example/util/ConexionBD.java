package org.example.util;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class ConexionBD {
    // añadimos allowPublicKeyRetrieval y desactivamos SSL si no lo necesitas
    private static final String URL =
            "jdbc:mariadb://localhost:3303/tiendajose" +
                    "?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USUARIO = "jose";
    private static final String CONTRASENA = "jose";

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(
                    "No se pudo cargar el driver MariaDB: " + e.getMessage()
            );
        }
    }

    public static Jdbi obtenerJdbi() {
        Jdbi jdbi = Jdbi.create(URL, USUARIO, CONTRASENA);
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }
}