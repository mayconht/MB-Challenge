package much.better.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Account implements Serializable {
    private static final long serialVersionUID = 1L;


    private String id;
    private BigDecimal amount;
    private String currency;

    List<Transactions> transactions = new ArrayList<>();

    public Account() {
    }

    public Account(final String id, final BigDecimal amount, final String currency) {
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

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(amount, account.amount) && Objects.equals(currency, account.currency) && Objects.equals(transactions, account.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, currency, transactions);
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("id", this.id)
                .append("amount", this.amount)
                .append("currency", this.currency)
                .append("transactions", this.transactions)
                .toString();
    }
}
