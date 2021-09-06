package com.cutty_pet.cutty_pet.dic.service;

import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.dic.entity.Dictionary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统字典表(Dictionary)表服务接口
 *
 * @author makejava
 * @since 2021-04-03 18:35:31
 */

public interface DictionaryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dictionary queryById(String id);
    List<Dictionary> queryByPage(PageParam pageParam);
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Dictionary> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param dictionary 实例对象
     * @return 实例对象
     */
    Dictionary insert(Dictionary dictionary);

    /**
     * 修改数据
     *
     * @param dictionary 实例对象
     * @return 实例对象
     */
    Dictionary update(Dictionary dictionary);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
