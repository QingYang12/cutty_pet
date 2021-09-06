package com.cutty_pet.cutty_pet.pet.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 宠物库存表
 * 库中现有宠物(PetStorage)实体类
 *
 * @author makejava
 * @since 2021-04-03 18:35:56
 */
public class PetStorage implements Serializable {
    private static final long serialVersionUID = 671496280246757781L;

    private String id;
    /**
     * 宠物code
     */
    private String petCode;
    /**
     * 库存数量
     */
    private Integer petNumber;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人编号
     */
    private String createUser;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新人编号
     */
    private String updateUser;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetCode() {
        return petCode;
    }

    public void setPetCode(String petCode) {
        this.petCode = petCode;
    }

    public Integer getPetNumber() {
        return petNumber;
    }

    public void setPetNumber(Integer petNumber) {
        this.petNumber = petNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

}
