package much.better.endpoints;

import com.google.inject.AbstractModule;
import much.better.Handlers.AccountHandler;

public class ApiModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ApiEndpoints.class);
        bind(AccountHandler.class);
    }
}
