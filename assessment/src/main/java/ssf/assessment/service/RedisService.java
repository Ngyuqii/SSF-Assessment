package ssf.assessment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import ssf.assessment.model.Order;

public class RedisService {

    //Inject the RedisTemplate into BoardGameService
    @Autowired
    RedisTemplate<String, Object> rTemplate;

    // public int saveOrder(final Order order) {
    //     rTemplate.opsForValue().set(.getOrderId(),
    // }
    
}
