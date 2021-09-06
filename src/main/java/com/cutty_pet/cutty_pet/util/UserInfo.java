package com.cutty_pet.cutty_pet.util;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户信息表
 * </p>

 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo {

    private static final long serialVersionUID = 1L;

    
    private Long id;

    /**
     * 账号
     */
    private String userPin;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 登录密码
     */
    private String userPassword;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 员工工号
     */
    private String employeeNo;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 城市
     */
    private String city;

    /**
     * 商家编号
     */
    private String orgCode;

    /**
     * 特殊标识1普通用户2商家主用户3大运营用户
     */
    private Integer specialFlag;

    /**
     * 上级ID
     */
    private String parentPin;

    /**
     * 是否是达达账号关联的海博临时账号
     */

    private Integer dadaFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人账号
     */
    private String createPin;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人账号
     */
    private String updatePin;

    /**
     * 数据版本号
     */
    private Integer sysVersion;

    /**
     * 是否逻辑删除
     */
    private Boolean yn;

    
    private List<String> userPins;

    /**
     * 特殊标识1普通用户2商家主用户3大运营用户4门店主管
     */
    
    private List<Integer> specialFlags;
    /**
     * 角色id
     */
    
    private Long roleId;
    /**
     * 角色名称
     */
    
    private String roleName;

    
    private Long qryStart;

    
    private Integer qryCount;

    
    private List<String> orgCodes;


    protected Serializable pkVal() {
        return this.id;
    }

}
