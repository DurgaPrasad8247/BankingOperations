package com.banking.model;

public class Account {
	 private int accountId;
	    private String accountHolder;
	    private int pin;
	    private double balance;

	    // Constructor with parameters
	    public Account(int accountId, String accountHolder, int pin, double balance) {
	        this.accountId = accountId;
	        this.accountHolder = accountHolder;
	        this.pin = pin;
	        this.balance = balance;
	    }

	    // Getters and setters for all fields
	    public int getAccountId() {
	        return accountId;
	    }

	    public void setAccountId(int accountId) {
	        this.accountId = accountId;
	    }

	    public String getAccountHolder() {
	        return accountHolder;
	    }

	    public void setAccountHolder(String accountHolder) {
	        this.accountHolder = accountHolder;
	    }

	    public int getPin() {
	        return pin;
	    }

	    public void setPin(int pin) {
	        this.pin = pin;
	    }

	    public double getBalance() {
	        return balance;
	    }

	    public void setBalance(double balance) {
	        this.balance = balance;
	    }


	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
