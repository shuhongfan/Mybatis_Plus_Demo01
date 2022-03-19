package com.shf.blog.service.impl;

import com.shf.blog.entity.User;
import com.shf.blog.mapper.UserMapper;
import com.shf.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 狂神说
 * @since 2022-03-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
