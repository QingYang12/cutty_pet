package com.cutty_pet.cutty_pet.devops.service;

/**
 * 客户收养宠物记录表(AdoptRecordHistory)表服务接口
 *
 * @author makejava
 * @since 2021-04-03 19:17:23
 */

public interface EmbeddedService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    void queryById(String id);

}
