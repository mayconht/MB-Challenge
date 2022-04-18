package much.better.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


public class Transactions implements Serializable {


    private String id;
    private Date timestamp;
    private String description;
    private BigDecimal amount;
    private String currency;

    public Transactions() {
    }

    public Transactions(final String id, final Date timestamp, final String description, final BigDecimal amount, final String currency) {
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
        if (!(o instanceof Transactions)) return false;
        Transactions that = (Transactions) o;
        return Objects.equals(id, that.id) && Objects.equals(timestamp, that.timestamp) && Objects.equals(description, that.description) && Objects.equals(amount, that.amount) && Objects.equals(currency, that.currency);
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
