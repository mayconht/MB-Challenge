package much.better.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class Transactions implements Serializable {


    private String id;
    private Date timestamp;
    private String description;
    private double amount;
    private String currency;

    public Transactions() {
    }

    public Transactions(final String id, final Date timestamp, final String description, final double amount, final String currency) {
        this.id = id;
        this.timestamp = timestamp;
        this.description = description;
        this.amount = amount;
        this.currency = currency;

    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
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
        if (!(o instanceof Transactions)) {
            return false;
        }
        final Transactions that = (Transactions) o;
        return Double.compare(that.amount, this.amount) == 0 && Objects.equals(this.id, that.id) && Objects.equals(this.timestamp, that.timestamp) && Objects.equals(this.description, that.description) && Objects.equals(this.currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.timestamp, this.description, this.amount, this.currency);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", this.id)
                .append("timestamp", this.timestamp)
                .append("description", this.description)
                .append("amount", this.amount)
                .append("currency", this.currency)
                .toString();
    }
}
