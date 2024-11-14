package org.example.repository;

import org.example.Datasource;
import org.example.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRepository {
    private static final String CREATE_TABLE_USER = """
            CREATE TABLE IF NOT EXISTS users (
            user_id SERIAL PRIMARY KEY,
            wallet_Id Integer,
            username varchar(50) UNIQUE NOT NULL,
            password varchar(50) NOT NULL,
            FOREIGN KEY (wallet_Id)
            REFERENCES wallet(wallet_id));
            """;


    public UserRepository() throws SQLException {
        // Initialize table on creation
        initTable();
    }

    public void initTable() throws SQLException {
        var statement = Datasource.getConnection().prepareStatement(UserRepository.CREATE_TABLE_USER);
        statement.execute();
        statement.close();
    }
    private static final String INSERT_SQL = """
            INSERT INTO users(username, password)
            VALUES (?, ?)
            """;
    private static final String DELETE_BY_ID = """
            DELETE FROM USERS
            WHERE user_id = ?
            and username=?
              """;

    private static final String FIND_BY_ID = """
            SELECT * FROM USERS
            WHERE user_id = ?
            """;

    private static final String FIND_BY_USERNAME = """
            SELECT * FROM USERS
            WHERE username = ?
            """;

    private static final String UPDATE_USER = """
            UPDATE USERS
            SET username = ? , password = ? , wallet_id = ?
            WHERE user_id = ?;
            """;

    public User save(User user) throws SQLException {
        var statement = Datasource.getConnection().prepareStatement(INSERT_SQL);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());

        statement.execute();
        statement.close();
        return user;
    }

    public void deleteById(int id) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, id);
            var affectedRows = statement.executeUpdate();
            System.out.println("# of Wallet deleted: " + affectedRows);
        }
    }

    public User findById(int id) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            User user = null;
            if (resultSet.next()) {
                int user_id = resultSet.getInt(1);
                int wallet_id = resultSet.getInt(2);
                String username = resultSet.getString(3);
                String password = resultSet.getString(4);

                user = new User (user_id, username, password, wallet_id);
            }

            return user;
        }
    }

    public User findByUsername(String username) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(FIND_BY_USERNAME)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            User user = null;
            if (resultSet.next()) {
                int user_id  = resultSet.getInt(1);
                int wallet_id = resultSet.getInt(2);
                String userName = resultSet.getString(3);
                String password = resultSet.getString(4);

                user = new User (user_id, userName, password, wallet_id);
            }

            return user;
        }
    }

    public User updateUser(User user) throws SQLException {
        var statement = Datasource.getConnection().prepareStatement(UPDATE_USER);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setInt(3,user.getWalletId());
        statement.setInt(4,user.getUserId());

        statement.execute();
        statement.close();
        return user;
    }
}