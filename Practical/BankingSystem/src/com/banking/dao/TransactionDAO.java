package com.banking.dao;
//TransactionDAO.java
import com.banking.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class TransactionDAO {
	private static final String INSERT_TRANSACTION = "INSERT INTO transaction_history (from_account_id, to_account_id, amount) VALUES (?, ?, ?)";
    private static final String GET_USER_TRANSACTIONS = "SELECT * FROM transaction_history WHERE from_account_id = ? OR to_account_id = ?";
    //private static final String GET_USER_DETAIL = "SELECT * FROM accounts WHERE account_id = ?";
    private static final String GET_ACCOUNT_BY_ID_AND_PIN = "SELECT * FROM accounts WHERE account_id = ? AND pin = ?";

    public void addTransaction(Transaction transaction) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRANSACTION)) {
            preparedStatement.setInt(1, transaction.getFromAccountId());
            preparedStatement.setInt(2, transaction.getToAccountId());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.executeUpdate();
        }
    }

    public List<Transaction> getUserTransactions(int accountId) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_TRANSACTIONS)) {
            preparedStatement.setInt(1, accountId);
            preparedStatement.setInt(2, accountId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Transaction> transactions = new ArrayList<>();

                while (resultSet.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(resultSet.getInt("transaction_id"));
                    transaction.setFromAccountId(resultSet.getInt("from_account_id"));
                    transaction.setToAccountId(resultSet.getInt("to_account_id"));
                    transaction.setAmount(resultSet.getDouble("amount"));

                    transactions.add(transaction);
                }

                return transactions;
            }
        }
    }
    

}
