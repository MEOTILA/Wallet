package org.example.repository;

import org.example.Datasource;
import org.example.entity.Wallet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletRepository {
    private static final String CREATE_TABLE_WALLET = """
            CREATE TABLE IF NOT EXISTS WALLET(
            wallet_id SERIAL PRIMARY KEY,
            address varchar(50) UNIQUE NOT NULL,
            balance DOUBLE PRECISION DEFAULT);
            """;

    public WalletRepository() throws SQLException {
        // Initialize table on creation
        initTable();
    }

    public void initTable() throws SQLException {
        var statement = Datasource.getConnection().prepareStatement(WalletRepository.CREATE_TABLE_WALLET);
        statement.execute();
        statement.close();
    }

    private static final String INSERT_SQL = """
            INSERT INTO WALLET(address, balance)
            VALUES (?, ?)
            """;
    private static final String DELETE_BY_ID_SQL = """
            DELETE FROM WALLET
            WHERE wallet_id = ?
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT * FROM WALLET
            WHERE wallet_id = ?
            """;

    private static final String UPDATE_WALLET_SQL = """
            UPDATE WALLET
            SET address = ? , balance = ?
            WHERE wallet_id = ?;
            """;



    public Wallet save(Wallet wallet) throws SQLException {
        var statement = Datasource.getConnection().prepareStatement(INSERT_SQL);
        statement.setString(1, wallet.getWalletAddress());
        statement.setDouble(2, wallet.getBalance());

        statement.execute();
        statement.close();
        return wallet;
    }

    public void deleteById(int id) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(DELETE_BY_ID_SQL)) {
            statement.setLong(1, id);
            var affectedRows = statement.executeUpdate();
            System.out.println("# of Wallet deleted: " + affectedRows);
        }
    }

    public Wallet findById(int id) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            Wallet wallet = null;
            if (resultSet.next()) {
                int wallet_id = resultSet.getInt(1);
                String address = resultSet.getString(2);
                double balance = resultSet.getDouble(3);
                wallet = new Wallet(wallet_id, address, balance);
            }
            return wallet;
        }
    }

    public Wallet updateWallet(Wallet wallet) throws SQLException {
        var statement = Datasource.getConnection().prepareStatement(UPDATE_WALLET_SQL);
        statement.setString(1, wallet.getWalletAddress());
        statement.setDouble(2, wallet.getBalance());
        statement.setInt(3,wallet.getWalletId());

        statement.execute();
        statement.close();
        return wallet;
    }
}
