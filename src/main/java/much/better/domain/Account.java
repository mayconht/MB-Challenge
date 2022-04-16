package much.better.domain;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;


public class Account implements Serializable {
    private static final long serialVersionUID = 1L;


    private String id;
    private double amount;
    private String currency;

    List<Transactions> transactions = new ArrayList<>();

    public Account() {
    }

    public Account(final String id, final double amount, final String currency) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(final List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

}
