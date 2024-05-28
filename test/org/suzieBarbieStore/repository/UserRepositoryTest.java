package org.suzieBarbieStore.repository;

import org.junit.jupiter.api.Test;
import org.suzieBarbieStore.models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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


    }

    @Test
    public void testUpdateUser(){
        Long userId = 3L;
        Long walletId = 200L;
        User user = userRepository.updateUser(userId, walletId);
        assertNotNull(user);
        assertEquals(200L, user.getWalletId());
    }

    @Test
    public void testFindUserById(){
        User user = userRepository.findById(3L).orElseThrow();
        assertNotNull(user);
        assertEquals(3L, user.getId());
    }
    @Test
    public void testDeleteUser(){
        Long userId = 3L;
        User user = userRepository.getUserBy(userId);
        assertNotNull(user);
        userRepository.deleteUser(userId);
        Optional<User> deletedUser = userRepository.findById(3L);
        assertTrue(deletedUser.isEmpty());
    }
    @Test
    public void testFindAllUsers(){
        List<User> users = userRepository.findAll();
        assertEquals(2, users.size());
    }

}