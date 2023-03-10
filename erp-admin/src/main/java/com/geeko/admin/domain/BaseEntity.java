package com.geeko.admin.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author JaneKim
 * @date 2023/3/8
 * @descript
 */
@MappedSuperclass
public class BaseEntity implements Serializable {


    private String id;
    private String createBy;
    private String updateBy;
    private Date createDate;
    private Date updateDate;


    public BaseEntity() {
        Date date = new Date();
        this.createDate = date;
        this.updateDate = date;
    }


    public BaseEntity(String id, String createBy) {
        this.id = id;
        this.createBy = createBy;
        this.updateBy = createBy;
        Date date = new Date();
        this.createDate = date;
        this.updateDate = date;
    }

    @Id
    @Column(name = "id",unique = true,length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "create_by")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Column(name = "update_by")
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    @Column(name = "update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
