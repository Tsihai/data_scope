package com.sihai.data_scope.mapper;

import com.sihai.data_scope.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author sihai
 * @since 2022-07-20
 */
public interface DeptMapper extends BaseMapper<Dept> {

    List<Dept> getAllDepts(Dept dept);
}
