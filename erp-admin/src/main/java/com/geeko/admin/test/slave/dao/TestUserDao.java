package com.geeko.admin.test.slave.dao;

import com.geeko.admin.test.slave.domain.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author JaneKim
 * @date 2023/3/8
 * @descript
 */
@Repository("testUserDao2")
public interface TestUserDao extends JpaRepository<TestUser, String> {

}
