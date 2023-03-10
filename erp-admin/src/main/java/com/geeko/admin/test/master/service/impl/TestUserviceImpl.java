package com.geeko.admin.test.master.service.impl;

import com.geeko.admin.test.master.dao.TestUserDao;
import com.geeko.admin.test.master.domain.TestUser;
import com.geeko.admin.test.master.service.TestUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author JaneKim
 * @date 2023/3/8
 * @descript
 */
@Service("test.TestUserService")
public class TestUserviceImpl implements TestUserService {

    @Resource(name = "testUserDao")
    private TestUserDao testUserDao;


    @Resource(name = "testUserDao2")
    private com.geeko.admin.test.slave.dao.TestUserDao testUserDao2;


    @Override
    public TestUser findyById(String id) {
        Optional<TestUser> user = testUserDao.findById(id);
        return user.orElseGet(TestUser::new);
    }

    @Override
    @Transactional(transactionManager = "transactionManager")
    public void addUser(TestUser testUser) {
        testUserDao.save(testUser);
        testUserDao2.save(new com.geeko.admin.test.slave.domain.TestUser(testUser.getName(), testUser.getAge(),
                testUser.getAddress()));
    }

    @Override
    @Transactional
    public void addUser2(TestUser testUser) {
        testUserDao.save(testUser);
    }
}
