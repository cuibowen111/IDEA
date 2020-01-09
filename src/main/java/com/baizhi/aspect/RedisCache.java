package com.baizhi.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component  //交由工厂管理
@Aspect     //声明这个是切面
public class RedisCache {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //环绕通知
    /*@Around("@annotation(com.baizhi.annotation.AddCache)")
    public Object arroud(ProceedingJoinPoint proceedingJoinPoint){
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        HashOperations hashOperations = redisTemplate.opsForHash();
        //namespace
        String namespace = proceedingJoinPoint.getTarget().getClass().getName();
        //方法名
        String method = proceedingJoinPoint.getSignature().getName();
        //获得所有的参数
        Object[] args = proceedingJoinPoint.getArgs();
        //拼接 StringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method);
        for (Object arg : args) {
            stringBuilder.append(arg.toString());
        }
        //判断是否有缓存
        if (hashOperations.hasKey(namespace,stringBuilder)){//存在
            System.out.println("获取缓存数据");
            Object o = hashOperations.get(namespace, stringBuilder);
            return o;
        }
        Object proceed = null;
        try {
            //不存在 添加缓存
            proceed = proceedingJoinPoint.proceed();
            System.out.println("添加缓存数据");
            hashOperations.put(namespace,stringBuilder,proceed);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }

    @After("@annotation(com.baizhi.annotation.ClearCache)")
    public void after(JoinPoint joinPoint){//清除缓存
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println("清除缓存数据");
        *//*redisTemplate.delete(name);*//*
        //StringRedisSerializer这种数据类型
        stringRedisTemplate.delete(name);
    }*/
}
