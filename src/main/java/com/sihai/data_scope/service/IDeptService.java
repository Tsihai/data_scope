package com.sihai.data_scope.service;

import com.sihai.data_scope.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author sihai
 * @since 2022-07-20
 */
public interface IDeptService extends IService<Dept> {

    List<Dept> getAllDepts(Dept dept);

}
