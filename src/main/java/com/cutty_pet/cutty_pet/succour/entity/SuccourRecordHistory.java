package com.cutty_pet.cutty_pet.succour.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 站点救助宠物记录表(SuccourRecordHistory)实体类
 *
 * @author makejava
 * @since 2021-04-03 19:17:36
 */
public class SuccourRecordHistory implements Serializable {
    private static final long serialVersionUID = -24176981407686591L;

    private String id;
    /**
     * 救助单号
     */
    private String succourOrderId;
    /**
     * 救助时间
     */
    private Date succourTime;
    /**
     * 救助人账号
     */
    private String succourUsername;
    /**
     * 救助备注
     */
    private String succourRemark;
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
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSuccourOrderId() {
        return succourOrderId;
    }

    public void setSuccourOrderId(String succourOrderId) {
        this.succourOrderId = succourOrderId;
    }

    public Date getSuccourTime() {
        return succourTime;
    }

    public void setSuccourTime(Date succourTime) {
        this.succourTime = succourTime;
    }

    public String getSuccourUsername() {
        return succourUsername;
    }

    public void setSuccourUsername(String succourUsername) {
        this.succourUsername = succourUsername;
    }

    public String getSuccourRemark() {
        return succourRemark;
    }

    public void setSuccourRemark(String succourRemark) {
        this.succourRemark = succourRemark;
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
}
