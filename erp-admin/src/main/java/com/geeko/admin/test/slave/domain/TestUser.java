package com.geeko.admin.test.slave.domain;

import com.geeko.admin.domain.BaseEntity;
import com.geeko.admin.utils.RandomUIDUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author JaneKim
 * @date 2023/3/8
 * @descript
 */
@Entity
@Table(name = "test_user")
public class TestUser extends BaseEntity {

    private String name;
    private int age;
    private String address;



    public TestUser() {

    }


    public TestUser(String name, int age, String address) {
        super(RandomUIDUtils.getFastUUID(), "");
        this.name = name;
        this.age = age;
        this.address = address;
    }



    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
