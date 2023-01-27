package ssf.assessment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfig {

    //Redis host, port, username, password values from application.properties
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private String redisPort;

    @Value("${spring.redis.username}")
    private String redisUsername;

    @Value("${spring.redis.password}")
    private String redisPassword;

    //Define the return redis template bean as single object throughout the runtime
    //Return Redis Template
    @Bean
    @Scope("singleton")
    public RedisTemplate<String, Object> redisTemplate(){

        //Redis Configuration
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        config.setPort(Integer.parseInt(redisPort));
        System.out.println(redisHost);
        System.out.println(redisPort);

        if(!redisUsername.isEmpty() && !redisPassword.isEmpty()){
            config.setUsername(redisUsername);
            config.setPassword(redisPassword);
        }
        config.setDatabase(0);

        //Jedis Configuration
        final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();

        //Redis-Jedis Connection
        final JedisConnectionFactory jedisFactory = new JedisConnectionFactory(config, jedisClient);
        jedisFactory.afterPropertiesSet();

        //Redis Template associate with the redis connection
        RedisTemplate<String, Object> rTemplate = new RedisTemplate<String, Object>();
        rTemplate.setConnectionFactory(jedisFactory);

        //Template Serialization
        //String type
        rTemplate.setKeySerializer(new StringRedisSerializer());
        rTemplate.setValueSerializer(new StringRedisSerializer());
        rTemplate.setHashKeySerializer(new StringRedisSerializer());
        rTemplate.setHashValueSerializer(new StringRedisSerializer());
        
        return rTemplate;
    }
}
