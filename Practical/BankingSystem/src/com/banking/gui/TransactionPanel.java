package com.banking.gui;
import com.banking.dao.AccountDAO;
import com.banking.dao.TransactionDAO;
import com.banking.model.Account;
import com.banking.model.Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
public class TransactionPanel extends JPanel implements Serializable{
	private static final long serialVersionUID=1L;
	private JTextField fromAccountField;
    private JTextField toAccountField;
    private JTextField amountField;
    private JTextField pinField;
    private JButton performTransactionButton;
    private JButton showTransactionHistoryButton;
    private JTextArea transactionHistoryTextArea;

    private AccountDAO accountDAO;
    private TransactionDAO transactionDAO;
    private JTextArea curbal;

    public TransactionPanel() {
        accountDAO = new AccountDAO();
        transactionDAO = new TransactionDAO();

        fromAccountField = new JTextField(10);
        toAccountField = new JTextField(10);
        amountField = new JTextField(10);
        pinField = new JTextField(10);
        performTransactionButton = new JButton("Perform Transaction");
        showTransactionHistoryButton = new JButton("Show Transaction History");
        transactionHistoryTextArea = new JTextArea(10, 30);
        

        performTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int fromAccountId = Integer.parseInt(fromAccountField.getText());
                    int toAccountId = Integer.parseInt(toAccountField.getText());
                    int pin = Integer.parseInt(pinField.getText());
                    double amount = Double.parseDouble(amountField.getText());

                    Account fromAccount = accountDAO.authenticateUser(fromAccountId, pin);

                    if (fromAccount != null && fromAccount.getBalance() >= amount) {
                        fromAccount.setBalance(fromAccount.getBalance() - amount);
                        accountDAO.updateAccount(fromAccount);

                        Account toAccount = accountDAO.getAccountById(toAccountId);
                        if (toAccount != null) {
                            toAccount.setBalance(toAccount.getBalance() + amount);
                            accountDAO.updateAccount(toAccount);

                            Transaction transaction = new Transaction();
                            transaction.setFromAccountId(fromAccountId);
                            transaction.setToAccountId(toAccountId);
                            transaction.setAmount(amount);

                            transactionDAO.addTransaction(transaction);

                            JOptionPane.showMessageDialog(null, "Transaction completed successfully!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid 'To' Account");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient balance or invalid 'From' Account");
                    }
                } catch (NumberFormatException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error performing transaction. Please check input values.");
                }
            }
        });

        showTransactionHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int accountId = Integer.parseInt(fromAccountField.getText());
                    int pin = Integer.parseInt(pinField.getText());

                    Account authenticatedUser = accountDAO.authenticateUser(accountId, pin);

                    if (authenticatedUser != null) {
                        showTransactionHistory(accountId);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid credentials");
                    }
                } catch (NumberFormatException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Error fetching transaction history. Please check input values.");
                }
            }
        });

        add(new JLabel("From Account:"));
        add(fromAccountField);
        add(new JLabel("To Account:"));
        add(toAccountField);
        add(new JLabel("Amount:"));
        add(amountField);
        add(new JLabel("PIN:"));
        add(pinField);
        add(performTransactionButton);
        add(showTransactionHistoryButton);
        add(new JScrollPane(transactionHistoryTextArea));
    }

    private void showTransactionHistory(int accountId) throws SQLException {
        List<Transaction> transactions = transactionDAO.getUserTransactions(accountId);

        StringBuilder historyText = new StringBuilder("Transaction History:\n");
        //historyText.append(String.format("Account Holder name: %s\n",getAccountHolder()));
        //historyText.append(String.format("Account id: %d\n",getAccountId()));
        //historyText.append(String.format("Current balance: %.2f\n",getBalance()));
        for (Transaction transaction : transactions) {
            historyText.append(String.format("Transaction ID: %d, From Account: %d, To Account: %d, Amount: %.2f\n",
                    transaction.getTransactionId(), transaction.getFromAccountId(),
                    transaction.getToAccountId(), transaction.getAmount()));
        }

        transactionHistoryTextArea.setText(historyText.toString());
    }
}
