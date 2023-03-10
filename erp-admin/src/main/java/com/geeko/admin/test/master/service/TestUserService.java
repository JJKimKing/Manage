package com.geeko.admin.test.master.service;

import com.geeko.admin.test.master.domain.TestUser;

/**
 * @author JaneKim
 * @date 2023/3/9
 * @descript
 */
public interface TestUserService {
    public TestUser findyById(String id);

    void addUser(TestUser testUser);

    void addUser2(TestUser testUser);
}
