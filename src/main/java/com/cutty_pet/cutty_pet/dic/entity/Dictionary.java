package com.cutty_pet.cutty_pet.dic.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 系统字典表(Dictionary)实体类
 *
 * @author makejava
 * @since 2021-04-03 18:35:31
 */
public class Dictionary implements Serializable {
    private static final long serialVersionUID = -44858525852319861L;
    /**
     * 主键
     */
    private String id;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 字段值
     */
    private String code;
    /**
     * 字段名
     */
    private String name;
    /**
     * 搜索页主图片jpg
     */
    private Object imgSearch;
    /**
     * 图片1jpg
     */
    private Object imgone;
    /**
     * 图片2jpg
     */
    private Object imgtwo;
    /**
     * 图片3jpg
     */
    private Object imgthree;
    /**
     * 宠物详细类型
     */
    private String petStyle;
    /**
     * 宠物种类
     */
    private String petType;
    /**
     * 描述
     */
    private String petDetails;
    /**
     * 简要描述
     */
    private String remark;
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

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getImgSearch() {
        return imgSearch;
    }

    public void setImgSearch(Object imgSearch) {
        this.imgSearch = imgSearch;
    }

    public Object getImgone() {
        return imgone;
    }

    public void setImgone(Object imgone) {
        this.imgone = imgone;
    }

    public Object getImgtwo() {
        return imgtwo;
    }

    public void setImgtwo(Object imgtwo) {
        this.imgtwo = imgtwo;
    }

    public Object getImgthree() {
        return imgthree;
    }

    public void setImgthree(Object imgthree) {
        this.imgthree = imgthree;
    }

    public String getPetStyle() {
        return petStyle;
    }

    public void setPetStyle(String petStyle) {
        this.petStyle = petStyle;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetDetails() {
        return petDetails;
    }

    public void setPetDetails(String petDetails) {
        this.petDetails = petDetails;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
