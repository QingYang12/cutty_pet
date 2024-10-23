package com.cutty_pet.cutty_pet.devops.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户收养宠物记录表(AdoptRecordHistory)实体类
 *
 * @author makejava
 * @since 2021-04-03 19:17:23
 */
public class DEVOPSEntity implements Serializable {
    private static final long serialVersionUID = 755225213345774048L;
    /**
     * 主键
     */
    private String id;
    /**
     * 救助单号
     */
    private String adoptOrderId;
    /**
     * 收养时间
     */
    private Date adoptTime;
    /**
     * 收养人账号
     */
    private String adoptUsername;
    /**
     * 收养备注
     */
    private String adoptRemark;
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

    private String petCode;
    private String telephone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdoptOrderId() {
        return adoptOrderId;
    }

    public void setAdoptOrderId(String adoptOrderId) {
        this.adoptOrderId = adoptOrderId;
    }

    public Date getAdoptTime() {
        return adoptTime;
    }

    public void setAdoptTime(Date adoptTime) {
        this.adoptTime = adoptTime;
    }

    public String getAdoptUsername() {
        return adoptUsername;
    }

    public void setAdoptUsername(String adoptUsername) {
        this.adoptUsername = adoptUsername;
    }

    public String getAdoptRemark() {
        return adoptRemark;
    }

    public void setAdoptRemark(String adoptRemark) {
        this.adoptRemark = adoptRemark;
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

    public String getPetCode() {
        return petCode;
    }

    public void setPetCode(String petCode) {
        this.petCode = petCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
