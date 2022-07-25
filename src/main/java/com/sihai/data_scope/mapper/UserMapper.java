package com.sihai.data_scope.mapper;

import com.sihai.data_scope.entity.Role;
import com.sihai.data_scope.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author sihai
 * @since 2022-07-20
 */
public interface UserMapper extends BaseMapper<User> {

    List<Role> getRolesByUid(Long userId);

    List<User> getAllUser(User user);
}
