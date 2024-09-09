package com.banking.model;

public class Transaction {
	 private int transactionId;
	    private int fromAccountId;
	    private int toAccountId;
	    private double amount;

	    // Constructors, getters, and setters

	    public int getTransactionId() {
	        return transactionId;
	    }

	    public void setTransactionId(int transactionId) {
	        this.transactionId = transactionId;
	    }

	    public int getFromAccountId() {
	        return fromAccountId;
	    }

	    public void setFromAccountId(int fromAccountId) {
	        this.fromAccountId = fromAccountId;
	    }

	    public int getToAccountId() {
	        return toAccountId;
	    }

	    public void setToAccountId(int toAccountId) {
	        this.toAccountId = toAccountId;
	    }

	    public double getAmount() {
	        return amount;
	    }

	    public void setAmount(double amount) {
	        this.amount = amount;
	    }

}
