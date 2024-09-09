package com.banking.gui;
import javax.swing.*;
public class BankingSystemApp {
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                JFrame frame = new JFrame("Banking System");
	                frame.setSize(600, 400);
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	                JTabbedPane tabbedPane = new JTabbedPane();
	                tabbedPane.addTab("Account Creation", new AccountCreationPanel());
	                tabbedPane.addTab("Transaction", new TransactionPanel());
	                tabbedPane.addTab("view account", new GetAccountDetailPanel());
	                tabbedPane.addTab("mini statement",new miniStatementPanel()); tabbedPane);
	                frame.getContentPane().add(tabbedPane);

	                frame.setVisible(true);
	            }
	        });
	    }
}
