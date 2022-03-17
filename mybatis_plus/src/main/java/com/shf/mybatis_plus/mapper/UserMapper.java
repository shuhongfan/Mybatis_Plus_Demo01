package com.shf.mybatis_plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shf.mybatis_plus.pojo.User;
import org.springframework.stereotype.Repository;

//在对应的mapper上面继承基本的类basemapper
@Repository
public interface UserMapper extends BaseMapper<User> {
//    所有的CRUD操作都已经编写完成
}
