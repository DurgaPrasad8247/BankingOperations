package com.banking.gui;

import com.banking.dao.AccountDAO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AccountCreationPanel extends JPanel {
	 private JTextField accountHolderField;
	    private JTextField pinField;
	    private JTextField balanceField;
	    private JButton createAccountButton;

	    private AccountDAO accountDAO;

	    public AccountCreationPanel() {
	        accountDAO = new AccountDAO();

	        accountHolderField = new JTextField(20);
	        pinField = new JTextField(10);
	        balanceField = new JTextField(10);
	        createAccountButton = new JButton("Create Account");

	        createAccountButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                Thread createAccountThread = new Thread(new Runnable() {
	                    @Override
	                    public void run() {
	                        try {
	                            String accountHolder = accountHolderField.getText();
	                            int pin = Integer.parseInt(pinField.getText());
	                            double balance = Double.parseDouble(balanceField.getText());

	                            // Create account
	                            accountDAO.createAccount(accountHolder, pin, balance);

	                            // Display success message
	                            JOptionPane.showMessageDialog(null, "Account created successfully!");
	                        } catch (NumberFormatException | SQLException ex) {
	                            ex.printStackTrace();
	                            // Handle validation or database error
	                            JOptionPane.showMessageDialog(null,
	                                    "Error creating account. Please check the input values.");
	                        }
	                    }
	                });

	                createAccountThread.start();
	            }
	        });

	        // Add components to the panel
	        add(new JLabel("Account Holder:"));
	        add(accountHolderField);
	        add(new JLabel("PIN:"));
	        add(pinField);
	        add(new JLabel("Balance:"));
	        add(balanceField);
	        add(createAccountButton);
	    }
}