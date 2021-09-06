package com.cutty_pet.cutty_pet.succour.dao;

import com.cutty_pet.cutty_pet.succour.entity.SuccourRecordHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 站点救助宠物记录表(SuccourRecordHistory)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-03 19:17:36
 */
@Mapper
public interface SuccourRecordHistoryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SuccourRecordHistory queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SuccourRecordHistory> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param succourRecordHistory 实例对象
     * @return 对象列表
     */
    List<SuccourRecordHistory> queryAll(SuccourRecordHistory succourRecordHistory);

    /**
     * 新增数据
     *
     * @param succourRecordHistory 实例对象
     * @return 影响行数
     */
    int insert(SuccourRecordHistory succourRecordHistory);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SuccourRecordHistory> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SuccourRecordHistory> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SuccourRecordHistory> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SuccourRecordHistory> entities);

    /**
     * 修改数据
     *
     * @param succourRecordHistory 实例对象
     * @return 影响行数
     */
    int update(SuccourRecordHistory succourRecordHistory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

