package org.suzieBarbieStore.repository;

import org.suzieBarbieStore.models.User;

import java.sql.*;

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
        String getIdSqlStatement = "select count(*) from users";
        String sql = "insert into users (id, wallet_id) values(?,?)";
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement((getIdSqlStatement));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long currentId = resultSet.getLong(1);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, currentId+1);
            if (user.getWalletId()!= null)
                preparedStatement.setLong(2, user.getWalletId());
            preparedStatement.execute();

            return getUserBy(currentId+1);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to connect to database");
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
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to connect to database");
        }
    }



}
