package com.cutty_pet.cutty_pet.pet.service;

import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.pet.dto.PetStorageDto;
import com.cutty_pet.cutty_pet.pet.entity.PetStorage;
import com.cutty_pet.cutty_pet.pet.vo.PetStorageVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 宠物库存表
 * 库中现有宠物(PetStorage)表服务接口
 *
 * @author makejava
 * @since 2021-04-03 18:35:57
 */

public interface PetStorageService {


    /**
     * 主页搜索宠物接口 API001
     *
     * @param
     * @return 实例对象
     */
    List<PetStorageDto> searchPet(PageParam petStorageVo);
    List<PetStorageDto> queryAll(PageParam petStorageVo);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PetStorage queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<PetStorage> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param petStorage 实例对象
     * @return 实例对象
     */
    PetStorage insert(PetStorage petStorage);

    /**
     * 修改数据
     *
     * @param petStorage 实例对象
     * @return 实例对象
     */
    PetStorage update(PetStorage petStorage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
