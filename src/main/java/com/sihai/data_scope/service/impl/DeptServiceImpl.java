package com.sihai.data_scope.service.impl;

import com.sihai.data_scope.annotation.DataScope;
import com.sihai.data_scope.entity.Dept;
import com.sihai.data_scope.mapper.DeptMapper;
import com.sihai.data_scope.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author sihai
 * @since 2022-07-20
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Autowired
    DeptMapper deptMapper;

    @Override
    @DataScope(deptAlias = "d")
    public List<Dept> getAllDepts(Dept dept) {
        return deptMapper.getAllDepts(dept);
    }
}
