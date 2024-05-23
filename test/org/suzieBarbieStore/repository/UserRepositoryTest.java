package org.suzieBarbieStore.repository;

import org.junit.jupiter.api.Test;
import org.suzieBarbieStore.models.User;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    UserRepository userRepository = new UserRepository();
    @Test
    public void testDatabaseConnection() throws SQLException {
        Connection connection = UserRepository.connect();
        assertNotNull(connection);
        System.out.println("connection -> "+ connection);

    }
    @Test
    public void saveUserTest() throws SQLException {
        User user = new User();
        user.setWalletId(1L);
        User savedUser = userRepository.saveUser(user);
        assertNotNull(savedUser);
    }

}