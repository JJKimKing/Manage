package com.geeko.admin.test.slave.service.impl;

import com.geeko.admin.test.slave.dao.TestUserDao;
import com.geeko.admin.test.slave.domain.TestUser;
import com.geeko.admin.test.slave.service.TestUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author JaneKim
 * @date 2023/3/8
 * @descript
 */
@Service("test2.TestUserService")
public class TestUserviceImpl implements TestUserService {

    @Resource(name = "testUserDao2")
    private TestUserDao testUserDao;


    @Override
    public TestUser findyById(String id) {
        return testUserDao.findById(id).get();
    }

}
