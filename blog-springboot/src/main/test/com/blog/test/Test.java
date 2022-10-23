package com.blog.test;

import com.minzheng.blog.BlogApplication;
import com.minzheng.blog.enums.UserAreaTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author chenjiashuai
 * @Description 血栓防治信息管理系统科室限制器
 * @Scope 血栓防治信息管理系统
 * @since 2022/9/24
 */
@SpringBootTest(classes = BlogApplication.class)
public class Test {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @org.junit.jupiter.api.Test
    void test1(){
        Object user_area = redisTemplate.opsForValue().get("user_area");
        System.out.println(user_area);
    }
}
