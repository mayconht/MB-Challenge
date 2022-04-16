package much.better.handlers;

import com.google.inject.Inject;
import much.better.service.AccountService;
import ratpack.handling.Context;
import ratpack.handling.Handler;

public class AccountHandler implements Handler {

    @Inject
    private AccountService accountService;

    @Override
    public void handle(final Context context) throws Exception {

        switch (context.getRequest().getPath().toLowerCase()) {
            case "login":
                System.out.println("LOGIN");
                break;
            case "balance":
                System.out.println("BALANCE");
                break;
            case "transactions":
                System.out.println("TRANSACTION");
                break;
            case "spend":
                System.out.println("SPEND");
                break;
            default:
                System.out.println("NONE");
                // TODO Should Return Bad Request
        }

    }
}
