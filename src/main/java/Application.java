import much.better.endpoints.ApiEndpoints;
import much.better.endpoints.ApiModule;
import much.better.errorHandlers.ErrorModule;
import much.better.service.ServiceModule;
import ratpack.guice.Guice;
import ratpack.server.RatpackServer;
import ratpack.server.ServerConfig;

public class Application {

    public static void main(final String... args) throws Exception {
        RatpackServer.start(server -> server
                .serverConfig(getServerConfig())
                .registry(Guice.registry(m -> m
                        .module(ErrorModule.class)
                        .module(ApiModule.class)
                        .module(ServiceModule.class)))
                .handlers(handler -> handler
                        .insert(ApiEndpoints.class)));
    }

    private static ServerConfig getServerConfig() throws Exception {
        final ratpack.server.ServerConfig serverConfig = ratpack.server.ServerConfig.of(config -> config
                .port(Integer.parseInt(System.getenv("RATPACK_PORT")))
                .development(Boolean.parseBoolean(System.getenv("RATPACK_DEV")))
                .connectTimeoutMillis(10000)
        );
        return serverConfig;
    }
}
