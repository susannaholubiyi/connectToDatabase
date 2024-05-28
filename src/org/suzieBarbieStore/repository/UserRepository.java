package org.suzieBarbieStore.repository;

import org.suzieBarbieStore.models.User;

import java.sql.*;
import java.util.Optional;
@SuppressWarnings(value = {"all"} )
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
    public  User saveUser(User user) throws SQLException {
        String sql = "insert into users (id, wallet_id) values(?,?)";
        try (Connection connection = connect()) {
           var  preparedStatement = connection.prepareStatement(sql);
           Long id = generateId();
            preparedStatement.setLong(1,id);
            preparedStatement.setObject(2, user.getWalletId());
            preparedStatement.execute();

            return getUserBy(id);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to connect to database");
        }

    }
    private Long generateId() {
        try (Connection connection = connect()){
            String sql ="SELECT max(id) FROM users";
            var statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Long lastIdGenerated = resultSet.getLong(1);
            return lastIdGenerated + 1;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public  User getUserBy(Long id){
        String sql = "select * from users where id=?";
        try (Connection connection = connect()) {
            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long userId = resultSet.getLong(1);
            Long walletId = resultSet.getLong(2);
            User user = new User();
            user.setId(userId);
            user.setWalletId(walletId);

            return user;
        }catch (SQLException e){
            return null;
        }
    }
    public Optional<User> findById(Long userId){
        User user = getUserBy(userId);
        if (user !=null) return Optional.of(user);
        return Optional.empty();
    }

    public User updateUser (Long userId, Long walletId){
        try(Connection connection = connect()){
            String sql = "UPDATE users SET wallet_id = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, walletId);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
            return  getUserBy(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
    public void deleteUser(Long userId) {
        try(Connection connection = connect()){
            String sql = "DELETE FROM users WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete");
        }
    }
}
