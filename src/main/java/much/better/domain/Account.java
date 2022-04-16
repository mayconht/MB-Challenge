package much.better.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
        return this.transactions;
    }

    public void setTransactions(final List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        final Account account = (Account) o;
        return Double.compare(account.amount, this.amount) == 0 && Objects.equals(this.id, account.id) && Objects.equals(this.currency, account.currency) && Objects.equals(this.transactions, account.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.amount, this.currency, this.transactions);
    }
}
