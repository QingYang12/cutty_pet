package com.cutty_pet.cutty_pet.pet.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 宠物库存表
 * 库中现有宠物(PetStorage)实体类
 *
 * @author makejava
 * @since 2021-04-03 18:35:56
 */
public class PetStorageDto implements Serializable {
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

    /**
     * 更新人编号
     */
    private String petStyle;
    /**
     * 更新人编号
     */
    private String petType;
    /**
     * 更新人编号
     */
    private String petDetails;
    /**
     * 更新人编号
     */
    private String remark;
    /**
     * 	搜索页主图片jpg
     */
    private String imgSearch;
    /**
     *	图片1jpg
     */
    private String imgone;
    /**
     * 图片2jpg
     */
    private String imgtwo;
    /**
     * 图片3jpg
     */
    private String imgthree;
    /**
     * 业务类型  pet宠物信息
     */
    private String businessType	;
    /**
     * 宠物名称
     */
    private String name	;














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

    public String getImgSearch() {
        return imgSearch;
    }

    public void setImgSearch(String imgSearch) {
        this.imgSearch = imgSearch;
    }

    public String getImgone() {
        return imgone;
    }

    public void setImgone(String imgone) {
        this.imgone = imgone;
    }

    public String getImgtwo() {
        return imgtwo;
    }

    public void setImgtwo(String imgtwo) {
        this.imgtwo = imgtwo;
    }

    public String getImgthree() {
        return imgthree;
    }

    public void setImgthree(String imgthree) {
        this.imgthree = imgthree;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
