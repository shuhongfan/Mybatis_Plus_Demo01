package com.shf.mybatis_plus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shf.mybatis_plus.mapper.UserMapper;
import com.shf.mybatis_plus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
//        参数是一个mapper,条件构造器,这里我们先不用null
//        查询全部用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

//    测试插入
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("武汉");
        user.setAge(3);
        user.setEmail("3434@22.com");
        int result = userMapper.insert(user); // 返回值是受影响的行数
        System.out.println(result);
    }

//    测试更新
    @Test
    public void testUpdate(){
//        通过条件自动拼接动态sql
        User user = new User();
        user.setId(2L);
        user.setName("关注公众号");
        user.setAge(129);

        int result = userMapper.updateById(user);
        System.out.println(result);
    }

//    测试乐观锁
    @Test
    public void testOptimisticLocker(){
//        1.查询用户信息
        User user = userMapper.selectById(1);

//        2.修改用户信息
        user.setName("shf");
        user.setEmail("234@eew.com");

//        3.执行更新操作
        userMapper.updateById(user);
    }

    //    测试乐观锁失败! 多线程下
    @Test
    public void testOptimisticLocker2(){
//        线程1
        User user = userMapper.selectById(1);
        user.setName("shf");
        user.setEmail("234@eew.com");

//        模拟另外一个线程执行了插队操作
        User user2 = userMapper.selectById(1);
        user2.setName("shf2");
        user2.setEmail("322@ew.com");

//        自旋锁来多次尝试提交!
//        如果没有乐观锁就会覆盖插队线程的值
        userMapper.updateById(user);
    }

//    测试查询
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

//    测试批量查询
    @Test
    public void testSelectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

//    条件查询
    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
//        自定义查询
        map.put("name","Tom");

        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    @Test
    public void testPage(){
//        参数一:当前页
//        参数二: 页面大小
        Page<User> page = new Page<>(1, 5);
        userMapper.selectPage(page,null);

        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
    }

//    测试删除
    @Test
    public void testDeleteById(){
        userMapper.deleteById("1504351221253787650");
    }

//    批量删除
    @Test
    public void testDeleteBatchId(){
        userMapper.deleteBatchIds(Arrays.asList(1,2,3));
    }

//    通过map删除
    @Test
    public void testDeleteMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","Java");
        userMapper.deleteByMap(map);
    }
}
