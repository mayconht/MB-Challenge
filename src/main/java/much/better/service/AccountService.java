package much.better.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import much.better.domain.Account;
import much.better.domain.Transactions;

import java.util.Date;
import java.util.UUID;

@Singleton
public class AccountService {

    @Inject
    private RedisService redisService;
    final ObjectMapper mapper = new ObjectMapper();

    public Account createAccount() { // TODO
        Account account = null;
        try {
            account = new Account(UUID.randomUUID().toString(), 5000.00, "USD");
            final Transactions transactions = new Transactions(UUID.randomUUID().toString(), new Date(), "Account Creation", 5000.00, "USD");
            account.getTransactions().add(transactions);
            this.redisService.jedisService().set(account.getId(), this.mapper.writeValueAsString(account));
        } catch (final JsonProcessingException ex) {
            System.err.println("Error Handling Json"); // TODO
        }
        return account;
    }

    public ObjectNode getAccountBalance(final String id) {
        final ObjectNode json = this.mapper.createObjectNode();

        try {
            final Account account = this.mapper.readValue(this.redisService.jedisService().get(id), Account.class);
            json.put("balance", account.getAmount());
            json.put("currency", account.getCurrency());
            return json;
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
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

}
