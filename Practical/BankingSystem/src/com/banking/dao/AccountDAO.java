package com.banking.dao;
import com.banking.model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AccountDAO {
	 private static final String SELECT_ACCOUNT_BY_ID = "SELECT * FROM accounts WHERE account_id = ?";
	    private static final String CREATE_ACCOUNT = "INSERT INTO accounts (account_holder, pin, balance) VALUES (?, ?, ?)";
	    private static final String AUTHENTICATE_USER = "SELECT * FROM accounts WHERE account_id = ? AND pin = ?";
	    private static final String UPDATE_ACCOUNT_BALANCE = "UPDATE accounts SET balance = ? WHERE account_id = ?";
	    private static final String GET_ACCOUNT_BY_ID_AND_PIN = "SELECT * FROM accounts WHERE account_id = ? AND pin = ?";
	    public Account getAccountById(int accountId) throws SQLException {
	        try (Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_BY_ID)) {
	            preparedStatement.setInt(1, accountId);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    // Using the constructor with parameters
	                    return new Account(
	                            resultSet.getInt("account_id"),
	                            resultSet.getString("account_holder"),
	                            resultSet.getInt("pin"),
	                            resultSet.getDouble("balance")
	                    );
	                }
	            }
	        }
	        return null;
	    }

	    public Account authenticateUser(int accountId, int pin) throws SQLException {
	        try (Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(AUTHENTICATE_USER)) {
	            preparedStatement.setInt(1, accountId);
	            preparedStatement.setInt(2, pin);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    // Using the constructor with parameters
	                    return new Account(
	                            resultSet.getInt("account_id"),
	                            resultSet.getString("account_holder"),
	                            resultSet.getInt("pin"),
	                            resultSet.getDouble("balance")
	                    );
	                }
	            }
	        }
	        return null;
	    }
	    
	    public void createAccount(String accountHolder, int pin, double balance) throws SQLException {
	        try (Connection connection = DatabaseConnection.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ACCOUNT)) {
	            preparedStatement.setString(1, accountHolder);
	            preparedStatement.setInt(2, pin);
	            preparedStatement.setDouble(3, balance);
	            preparedStatement.executeUpdate();
	        }
	    }



	    public void updateAccount(Account account) throws SQLException {
	        try (Connection connection = DatabaseConnection.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_BALANCE)) {
	            preparedStatement.setDouble(1, account.getBalance());
	            preparedStatement.setInt(2, account.getAccountId());
	            preparedStatement.executeUpdate();
	        }
	        
	    }
	    public Account getAccountByIdAndPin(int accountId, int pin) throws SQLException {
	        try (Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_ID_AND_PIN)) {
	            preparedStatement.setInt(1, accountId);
	            preparedStatement.setInt(2, pin);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    return new Account(
	                            resultSet.getInt("account_id"),
	                            resultSet.getString("account_holder"),
	                            resultSet.getInt("pin"),
	                            resultSet.getDouble("balance")
	                    );
	                } else {
	                    throw new SQLException("Account not found");
	                }
	            }
	        }
	    }

}
