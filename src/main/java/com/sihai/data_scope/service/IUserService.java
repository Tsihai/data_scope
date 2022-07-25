package com.sihai.data_scope.service;

import com.sihai.data_scope.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author sihai
 * @since 2022-07-20
 */
public interface IUserService extends IService<User> {

    List<User> getAllUser(User user);
}
