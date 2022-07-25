package com.sihai.data_scope.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sihai.data_scope.annotation.DataScope;
import com.sihai.data_scope.entity.User;
import com.sihai.data_scope.mapper.UserMapper;
import com.sihai.data_scope.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author sihai
 * @since 2022-07-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService, UserDetailsService {

    @Autowired
    UserMapper userMapper;

    /**
     * 按用户名加载用户
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.lambda().eq(User::getUsername, username);
        User user = getOne(qw);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 根据用户id查询用户角色
        user.setRoles(userMapper.getRolesByUid(user.getUserId()));
        return user;
    }

    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<User> getAllUser(User user) {
        return userMapper.getAllUser(user);
    }
}
