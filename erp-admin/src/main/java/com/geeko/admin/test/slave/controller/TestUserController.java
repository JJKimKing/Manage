package com.geeko.admin.test.slave.controller;

import com.geeko.admin.test.slave.service.TestUserService;
import com.geeko.admin.test.slave.domain.TestUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author JaneKim
 * @date 2023/3/8
 * @descript
 */
@RestController("test2.TestUserController")
@RequestMapping("/test2/user")
public class TestUserController {

    @Resource(name = "test2.TestUserService")
    private TestUserService testUserService;


    @GetMapping("/get-user/{id}")
    public TestUser findByUser(@PathVariable("id") String id) {
        return testUserService.findyById(id);
    }

}
