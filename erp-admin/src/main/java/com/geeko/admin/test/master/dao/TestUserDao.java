package com.geeko.admin.test.master.dao;

import com.geeko.admin.test.master.domain.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author JaneKim
 * @date 2023/3/8
 * @descript
 */
@Repository("testUserDao")
public interface TestUserDao extends JpaRepository<TestUser, String> {

}
