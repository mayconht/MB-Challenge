package much.better.handlers;

import com.google.inject.Inject;
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

        final String bearer = context.getRequest().getHeaders().get("Authorization").isEmpty() ? "" : context.getRequest().getHeaders().get("Authorization").replace("Bearer", "").trim();
        final String id = bearer.isEmpty() ? "" : this.jwtService.getClaimFromToken(bearer, "UUID");


        switch (context.getRequest().getPath().toLowerCase()) {
            case "login":
                final Map<String, String> token = new HashMap<>();
                token.put("token", this.jwtService.generateJWTToken(this.accountService.createAccount().getId()));
                context.render(Jackson.json(token));
                break;

            case "balance":
                if (!bearer.isEmpty()) {
                    if (!this.jwtService.verifyJWTToken(bearer)) {
                        System.out.println("Invalid Token: " + bearer);//TODO error
                    }
                    context.render(Jackson.json(this.accountService.getAccountBalance(id)));

                } else {
                    System.out.println("Invalid Token: " + bearer); //TODO error
                }
                break;
            case "transactions":
                if (!bearer.isEmpty()) {
                    if (!this.jwtService.verifyJWTToken(bearer)) {
                        System.out.println("Invalid Token: " + bearer);//TODO error
                    }
                    context.render(Jackson.json(this.accountService.getAccountTransactions(id)));
                } else {
                    System.out.println("Invalid Token: " + bearer); //TODO error
                }
                break;
            case "spend":
                if (!bearer.isEmpty()) {
                    if (!this.jwtService.verifyJWTToken(bearer)) {
                        System.out.println("Invalid Token: " + bearer);//TODO error
                    }

                    context.getRequest().getBody().then(bd -> {
                        if (this.accountService.insertNewTransaction(id, bd.getText())) {
                            context.render("ok");
                        } else {
                            context.clientError(400);
                        }
                    });


                }
                break;
            default:
                System.out.println("NONE");
                // TODO Should Return Bad Request
        }
    }
}
