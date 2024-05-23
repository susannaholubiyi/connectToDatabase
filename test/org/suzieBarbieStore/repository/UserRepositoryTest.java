package org.suzieBarbieStore.repository;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    @Test
    public void testDatabaseConnection() throws SQLException {
        Connection connection = UserRepository.connect();
        assertNotNull(connection);
        System.out.println("connection -> "+ connection);

    }
    @Test
    public void saveUserTest(){

    }

}