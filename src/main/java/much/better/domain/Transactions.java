package much.better.domain;

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
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
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
