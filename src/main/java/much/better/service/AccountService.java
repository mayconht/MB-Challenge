package much.better.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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


}
