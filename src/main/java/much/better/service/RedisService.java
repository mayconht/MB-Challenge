package much.better.service;

import com.google.inject.Singleton;
import redis.clients.jedis.Jedis;

@Singleton
class RedisService {
    private final Jedis jedis = new Jedis(System.getenv("REDIS"));

    public Jedis jedisService() {
        return this.jedis;
    }

}
