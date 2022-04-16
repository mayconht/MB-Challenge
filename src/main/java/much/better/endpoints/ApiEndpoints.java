package much.better.endpoints;

import much.better.handlers.AccountHandler;
import ratpack.func.Action;
import ratpack.handling.Chain;

public class ApiEndpoints implements Action<Chain> {
    @Override
    public void execute(final Chain chain) throws Exception {
        chain.post("login", AccountHandler.class);
        chain.get("balance", AccountHandler.class);
        chain.get("transactions", AccountHandler.class);
        chain.post("spend", AccountHandler.class);
    }
}
