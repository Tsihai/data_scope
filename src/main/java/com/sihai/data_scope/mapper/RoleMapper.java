package com.sihai.data_scope.mapper;

import com.sihai.data_scope.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author sihai
 * @since 2022-07-20
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getAllRole(Role role);
}
