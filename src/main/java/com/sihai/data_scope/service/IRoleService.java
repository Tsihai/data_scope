package com.sihai.data_scope.service;

import com.sihai.data_scope.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author sihai
 * @since 2022-07-20
 */
public interface IRoleService extends IService<Role> {

    List<Role> getAllRole(Role role);
}
