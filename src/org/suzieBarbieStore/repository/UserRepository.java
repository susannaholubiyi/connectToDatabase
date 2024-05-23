package org.suzieBarbieStore.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserRepository {
    public static Connection connect(){
        String url = "jdbc:postgresql://localhost:5432/suzies_store";
        //postgres -> jdbc:postgresql://localhost:5432
        //mysql-> jdbc:mysql://localhost:3306/suzies_stores?createDatabaseIfNotExist=true
        String username = "postgres";
        String password = "password";
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
