package com.banking.gui;

import com.banking.dao.AccountDAO;
import com.banking.model.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class GetAccountDetailPanel extends JPanel {
    private JTextField accountIdField;
    private JTextField pinField;
    private JButton getAccountDetailButton;
    private JTextArea accountDetailTextArea;

    private AccountDAO accountDAO;

    public GetAccountDetailPanel() {
        accountDAO = new AccountDAO();

        accountIdField = new JTextField(10);
        pinField = new JTextField(10);
        getAccountDetailButton = new JButton("Get Account Detail");
        accountDetailTextArea = new JTextArea(5, 30);

        getAccountDetailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int accountId = Integer.parseInt(accountIdField.getText());
                    int pin = Integer.parseInt(pinField.getText());

                    Account account = accountDAO.getAccountByIdAndPin(accountId, pin);

                    if (account != null) {
                        displayAccountDetails(account);
                    } else {
                        JOptionPane.showMessageDialog(null, "Account not found");
                    }
                } catch (NumberFormatException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error fetching account details. Please check input values.");
                }
            }
        });

        add(new JLabel("Account ID:"));
        add(accountIdField);
        add(new JLabel("PIN:"));
        add(pinField);
        add(getAccountDetailButton);
        add(new JScrollPane(accountDetailTextArea));
    }

    private void displayAccountDetails(Account account) {
        accountDetailTextArea.setText(String.format(
                "Account ID: %d\nAccount Holder: %s\nCurrent Balance: $%.2f",
                account.getAccountId(),
                account.getAccountHolder(),
                account.getBalance()));
    }
}