package com.sihai.data_scope.service.impl;

import com.sihai.data_scope.annotation.DataScope;
import com.sihai.data_scope.entity.Role;
import com.sihai.data_scope.mapper.RoleMapper;
import com.sihai.data_scope.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author sihai
 * @since 2022-07-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<Role> getAllRole(Role role) {
        return roleMapper.getAllRole(role);
    }
}
