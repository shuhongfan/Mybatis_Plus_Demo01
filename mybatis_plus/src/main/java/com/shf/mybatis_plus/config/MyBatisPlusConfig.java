package com.shf.mybatis_plus.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 乐观锁：顾名思义十分乐观，它总是认为不会出现问题，无论干什么都不会上锁！如果出现了问题，再次更新值测试
 * 悲观锁：顾名思义十分悲观，它总是以为会出现问题，无论干什么都会上锁！再去操作
 *
 * 乐观锁机制
 * 乐观锁实现方式
 * 取出记录时，获取当前version
 * 更新时，带上这个version
 * 执行更新时，set version=new version where version=oldversion
 * 如果version不对，就更新失败
 */
@MapperScan("com.shf.mapper") // 扫描我们的mapper文件夹
@EnableTransactionManagement // 开启事务
@Configuration // 配置类
public class MyBatisPlusConfig {
//    注册乐观锁
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

//    分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

//    逻辑删除组件
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * SQl执行效率插件
     */
    @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(100); // 设置SQL执行的最大时间,如果超过了则不执行,抛出异常
        performanceInterceptor.setFormat(true); // 是否格式化代码
        return performanceInterceptor;
    }
}
