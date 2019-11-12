package com.tensquare.user.service;

import com.tensquare.user.dao.UserDao;
import com.tensquare.user.pojo.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import utils.IdWorker;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private UserDao userDao;

    /*
    发送短信验证码
     */
    public void sendSms(String mobile){
        //生成六位数验证码
        Random random = new Random();
        int max=999999;
        int min=100000;
        int maxInt = random.nextInt(max);
        if (min < maxInt || maxInt < max){
            maxInt = maxInt + min;
        }
        System.out.println(mobile + "收到的验证码是：" + maxInt);
        //发送到redis，设置五分钟过期
        redisTemplate.opsForValue().set("smscode_" + mobile,maxInt+"",5, TimeUnit.MINUTES);
        //将验证码和手机号发送到mq中
        Map map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("smscode",maxInt);
        rabbitTemplate.convertAndSend("sms",map);
    }

    /*
    用户注册
     */
    public void addUser(User user,String code){
        //判断验证码是否正确
        String syscode = (String) redisTemplate.opsForValue().get("smscode_" + user.getMobile());
        if (syscode == null){
            throw new RuntimeException("点击获取验证码");
        }
        if (!syscode.equals(code)){
            throw new RuntimeException("验证码不正确");
        }
        user.setId(idWorker.nextId() + "");
        user.setFollowcount(0);
        user.setFanscount(0);
        user.setOnline(0L);
        user.setRegdate(new Date());
        user.setUpdatedate(new Date());
        user.setLastdate(new Date());
        userDao.save(user);
    }
}
