package much.better.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import much.better.domain.Account;
import much.better.domain.Transactions;
import much.better.errorHandlers.errors.BaseException;
import much.better.errorHandlers.errors.NoSuchAccountException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Singleton
public class AccountService {

    @Inject
    private RedisService redisService;
    final ObjectMapper mapper = new ObjectMapper();


    public Account createAccount() {
        Account account = null;
        try {
            account = new Account(UUID.randomUUID().toString(), new BigDecimal(System.getenv("ACCOUNT_INITIAL_AMOUNT")), System.getenv("ACCOUNT_CURRENCY"));
            final Transactions transactions = new Transactions(UUID.randomUUID().toString(), new Date(), "Account Creation",new BigDecimal( System.getenv("ACCOUNT_INITIAL_AMOUNT")), System.getenv("ACCOUNT_CURRENCY"));
            account.getTransactions().add(transactions);
            this.redisService.jedisService().set(account.getId(), this.mapper.writeValueAsString(account));
        } catch (final JsonProcessingException ex) {
            throw new BaseException(500, "Internal Server Error", ex.getMessage());
        }
        return account;
    }

    public ObjectNode getAccountBalance(final String id) {
        final ObjectNode json = this.mapper.createObjectNode();
        final Account account = getAccountById(id);
        json.put("balance", account.getAmount());
        json.put("currency", account.getCurrency());
        return json;
    }

    public ObjectNode getAccountTransactions(final String id) {
        final ObjectNode json = this.mapper.createObjectNode();

        try {
            final Account account = this.mapper.readValue(this.redisService.jedisService().get(id), Account.class);
            final ArrayNode arraynode = json.putArray("transactions");
            json.put("id", account.getId());
            account.getTransactions().forEach(arraynode::addPOJO);
            return json;
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean insertNewTransaction(final String id, final String body) throws ParseException, JsonProcessingException {
        final JSONObject jbody = new JSONObject(body);
        final Account account = getAccountById(id);

        if (!jbody.getString("currency").equals(account.getCurrency())) {
            return false;
        }
        if (account.getAmount().compareTo(new BigDecimal(jbody.getString("amount"))) < 0) {
            return false;
        }

        final Transactions transactions = new Transactions(
                UUID.randomUUID().toString(),
                dateConverter(jbody.getString("date")),
                jbody.getString("description"),
                new BigDecimal(jbody.getString("amount")),
                jbody.getString("currency"));

        account.setAmount(account.getAmount().subtract(new BigDecimal(jbody.getString("amount"))));
        account.getTransactions().add(transactions);


        this.redisService.jedisService().set(account.getId(), this.mapper.writeValueAsString(account));

        return true;
    }

    private Account getAccountById(final String id) {
        Account account = null;
        try {
            account = this.mapper.readValue(this.redisService.jedisService().get(id), Account.class);
        } catch (final Exception e) {
            throw new NoSuchAccountException();
        }
        return account;
    }

    private Date dateConverter(final String date) throws ParseException {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        return formatter.parse(date);
    }
}
