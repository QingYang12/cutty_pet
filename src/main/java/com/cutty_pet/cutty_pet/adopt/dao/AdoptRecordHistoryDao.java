package com.cutty_pet.cutty_pet.adopt.dao;

import com.cutty_pet.cutty_pet.adopt.entity.AdoptRecordHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户收养宠物记录表(AdoptRecordHistory)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-03 19:17:23
 */
@Mapper
public interface AdoptRecordHistoryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AdoptRecordHistory queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AdoptRecordHistory> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param adoptRecordHistory 实例对象
     * @return 对象列表
     */
    List<AdoptRecordHistory> queryAll(AdoptRecordHistory adoptRecordHistory);

    /**
     * 新增数据
     *
     * @param adoptRecordHistory 实例对象
     * @return 影响行数
     */
    int insert(AdoptRecordHistory adoptRecordHistory);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AdoptRecordHistory> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AdoptRecordHistory> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AdoptRecordHistory> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<AdoptRecordHistory> entities);

    /**
     * 修改数据
     *
     * @param adoptRecordHistory 实例对象
     * @return 影响行数
     */
    int update(AdoptRecordHistory adoptRecordHistory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

