package com.sihai.data_scope.controller;

import com.sihai.data_scope.entity.Dept;
import com.sihai.data_scope.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author sihai
 * @since 2022-07-20
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    IDeptService deptService;

    /**
     * 查询所有部门
     * @return
     */
    @GetMapping("/list")
    public List<Dept> getAllDepts(Dept dept){
        return deptService.getAllDepts(dept);
    }

}
