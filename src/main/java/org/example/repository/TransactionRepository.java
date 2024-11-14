package org.example.repository;

import org.example.Datasource;

import java.sql.SQLException;

public class TransactionRepository {
    private static final String CREATE_TABLE_TRANSACTION = """
            CREATE TABLE IF NOT EXISTS transaction (
            wallet_id INTEGER,
            transaction_id SERIAL PRIMARY KEY,
            amount DOUBLE PRECISION NOT NULL,
            type varchar(10) NOT NULL,
            FOREIGN KEY (wallet_Id)
            REFERENCES wallet(wallet_id));
            """;

    public TransactionRepository() throws SQLException {
        // Initialize table on creation
        initTable();
    }

    public void initTable() throws SQLException {
        var statement = Datasource.getConnection().prepareStatement(TransactionRepository.CREATE_TABLE_TRANSACTION);
        statement.execute();
        statement.close();
    }

}
