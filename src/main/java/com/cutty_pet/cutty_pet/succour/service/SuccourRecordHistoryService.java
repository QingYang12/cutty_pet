package com.cutty_pet.cutty_pet.succour.service;

import com.cutty_pet.cutty_pet.adopt.entity.AdoptRecordHistory;
import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.succour.entity.SuccourRecordHistory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 站点救助宠物记录表(SuccourRecordHistory)表服务接口
 *
 * @author makejava
 * @since 2021-04-03 19:17:36
 */

public interface SuccourRecordHistoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SuccourRecordHistory queryById(String id);
    void sitSuccour(SuccourRecordHistory succourRecordHistory);
    List<SuccourRecordHistory> queryByPage(PageParam pageParam);
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SuccourRecordHistory> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param succourRecordHistory 实例对象
     * @return 实例对象
     */
    SuccourRecordHistory insert(SuccourRecordHistory succourRecordHistory);

    /**
     * 修改数据
     *
     * @param succourRecordHistory 实例对象
     * @return 实例对象
     */
    SuccourRecordHistory update(SuccourRecordHistory succourRecordHistory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
