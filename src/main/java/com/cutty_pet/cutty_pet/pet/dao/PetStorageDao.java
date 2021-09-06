package com.cutty_pet.cutty_pet.pet.dao;

import com.cutty_pet.cutty_pet.pet.dto.PetStorageDto;
import com.cutty_pet.cutty_pet.pet.entity.PetStorage;
import com.cutty_pet.cutty_pet.pet.vo.PetStorageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 宠物库存表
 * 库中现有宠物(PetStorage)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-03 18:35:57
 */
@Mapper
public interface PetStorageDao {

    /**
     * 主页搜索宠物接口 API001
     *
     * @param
     * @return 实例对象
     */
    List<PetStorageDto>  searchPet(PetStorageVo petStorageVo);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PetStorage queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<PetStorage> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param petStorage 实例对象
     * @return 对象列表
     */
    List<PetStorage> queryAll(PetStorage petStorage);

    /**
     * 新增数据
     *
     * @param petStorage 实例对象
     * @return 影响行数
     */
    int insert(PetStorage petStorage);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<PetStorage> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<PetStorage> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<PetStorage> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<PetStorage> entities);

    /**
     * 修改数据
     *
     * @param petStorage 实例对象
     * @return 影响行数
     */
    int update(PetStorage petStorage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

