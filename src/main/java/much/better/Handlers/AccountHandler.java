package much.better.Handlers;

import com.google.inject.Inject;
import much.better.errorHandlers.errors.BaseException;
import much.better.errorHandlers.errors.NoSuchAccountException;
import much.better.service.AccountService;
import much.better.service.JwtService;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.jackson.Jackson;

import java.util.HashMap;
import java.util.Map;

public class AccountHandler implements Handler {

    @Inject
    private AccountService accountService;

    @Inject
    private JwtService jwtService;

    @Override
    public void handle(final Context context) throws Exception {
        String bearer = "";
        String id = "";

        if (context.getRequest().getHeaders().getNames().contains("Authorization")) {
            bearer = context.getRequest().getHeaders().get("Authorization").isEmpty() ? "" : context.getRequest().getHeaders().get("Authorization").replace("Bearer", "").trim();
            id = bearer.isEmpty() ? "" : this.jwtService.getClaimFromToken(bearer, "UUID");
        } else {
            if (!context.getRequest().getPath().equals("login")) {
                throw new NoSuchAccountException();
            }
        }


        switch (context.getRequest().getPath().toLowerCase()) {
            case "login":
                final Map<String, String> token = new HashMap<>();
                token.put("token", this.jwtService.generateJWTToken(this.accountService.createAccount().getId()));
                context.render(Jackson.json(token));
                break;
            case "balance":
                context.render(Jackson.json(this.accountService.getAccountBalance(id)));
                break;
            case "transactions":
                context.render(Jackson.json(this.accountService.getAccountTransactions(id)));
                break;
            case "spend":

                final String finalId = id;
                context.getRequest().getBody().then(bd -> {
                    if (this.accountService.insertNewTransaction(finalId, bd.getText())) {
                        context.getResponse().status(201).send();
                    } else {
                        throw new BaseException(500, "No funds for account or wrong currency");
                    }
                });
                break;
            default:

        }
    }
}
