package com.cutty_pet.cutty_pet.adopt.service;

import com.cutty_pet.cutty_pet.adopt.entity.AdoptRecordHistory;
import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.dic.entity.Dictionary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户收养宠物记录表(AdoptRecordHistory)表服务接口
 *
 * @author makejava
 * @since 2021-04-03 19:17:23
 */

public interface AdoptRecordHistoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AdoptRecordHistory queryById(String id);
    List<AdoptRecordHistory> queryByPage(PageParam pageParam);
    void customerAdopt(AdoptRecordHistory adoptRecordHistory)throws Exception;

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AdoptRecordHistory> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param adoptRecordHistory 实例对象
     * @return 实例对象
     */
    AdoptRecordHistory insert(AdoptRecordHistory adoptRecordHistory);

    /**
     * 修改数据
     *
     * @param adoptRecordHistory 实例对象
     * @return 实例对象
     */
    AdoptRecordHistory update(AdoptRecordHistory adoptRecordHistory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
