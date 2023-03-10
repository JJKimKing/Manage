package com.geeko.admin.test.master.controller;

import com.geeko.admin.reponse.JResult;
import com.geeko.admin.test.master.domain.TestUser;
import com.geeko.admin.test.master.service.TestUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author JaneKim
 * @date 2023/3/8
 * @descript
 */
@RestController("test.TestUserController")
@RequestMapping("/test/user")
public class TestUserController {

    @Resource(name = "test.TestUserService")
    private TestUserService testUserService;


    @GetMapping("/get-user/{id}")
    public TestUser findByUser(@PathVariable("id") String id) {
        return testUserService.findyById(id);
    }

    @PostMapping("/add-user")
    public JResult addTestUser(@RequestBody TestUser testUser) {
        testUserService.addUser(testUser);
        return JResult.SUCCESS;
    }

    @PostMapping("/add-user2")
    public JResult addTestUser2(@RequestBody TestUser testUser) {
        testUserService.addUser2(testUser);
        return JResult.SUCCESS;
    }

}
