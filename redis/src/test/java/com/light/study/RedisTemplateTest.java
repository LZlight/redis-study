package com.light.study;

import com.light.study.redis.UserApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author light
 * @Date 2019-06-11 18:54
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = UserApplication.class)
public class RedisTemplateTest {


    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void testRedis(){
        String name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }


}
