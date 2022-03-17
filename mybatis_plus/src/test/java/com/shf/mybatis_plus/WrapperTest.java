package com.shf.mybatis_plus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shf.mybatis_plus.mapper.UserMapper;
import com.shf.mybatis_plus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        QueryWrapper<User> userQueryWrapper = wrapper.isNotNull("name")
                .isNotNull("email")
                .ge("age", 12);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void test2(){
//        查询名字
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "shf");
//        查询就一个数据
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void test3(){
//        查询年龄在20-30岁之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.gt("age", "20");
//        wrapper.lt("age", "30");
        wrapper.between("age", 20,30);

        Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

//    模糊查询
    @Test
    public void test4(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        左和右 t%
        wrapper
                .notLike("name", "e")
                .likeRight("email","t");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test5(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        id在子查询中查询出来
        wrapper.inSql("id", "select id from user where id<3");

        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }

    @Test
    public void test6(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        通过id进行排序
        wrapper.orderByDesc("id");

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


}
