package much.better.service;

import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AccountService.class);
        bind(RedisService.class);
    }
}
