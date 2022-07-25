package com.sihai.data_scope.controller;

import com.sihai.data_scope.entity.User;
import com.sihai.data_scope.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author sihai
 * @since 2022-07-20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/list")
    public List<User> getAllUser(User user) {
        return userService.getAllUser(user);
    }


}
