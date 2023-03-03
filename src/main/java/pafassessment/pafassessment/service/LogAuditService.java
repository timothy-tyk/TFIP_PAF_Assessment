package pafassessment.pafassessment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import pafassessment.pafassessment.model.Transfer;

@Service
public class LogAuditService {
  @Autowired
  RedisTemplate<String,Object> redisTemplate;

  public void logToRedis(Transfer trf){
    redisTemplate.opsForValue().set(trf.getTransactionId(), Transfer.toJson(trf));
    System.out.println(Transfer.toJson(trf));
  }
  
}
