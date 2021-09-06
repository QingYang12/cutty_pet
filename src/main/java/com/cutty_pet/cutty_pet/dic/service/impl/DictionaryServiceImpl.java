package com.cutty_pet.cutty_pet.dic.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.common.UUIDUtil;
import com.cutty_pet.cutty_pet.customer.entity.Customer;
import com.cutty_pet.cutty_pet.dic.entity.Dictionary;
import com.cutty_pet.cutty_pet.dic.dao.DictionaryDao;
import com.cutty_pet.cutty_pet.dic.service.DictionaryService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * 系统字典表(Dictionary)表服务实现类
 *
 * @author makejava
 * @since 2021-04-03 18:35:31
 */
@Service("dictionaryServiceImpl")
public class DictionaryServiceImpl implements DictionaryService {
    @Resource
    private DictionaryDao dictionaryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Dictionary queryById(String id) {
        return this.dictionaryDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Dictionary> queryAllByLimit(int offset, int limit) {
        return this.dictionaryDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<Dictionary> queryByPage(PageParam pageParam) {
        JSONObject json = (JSONObject) JSON.toJSON(pageParam.getParam());
        Dictionary param = (Dictionary) JSON.toJavaObject(json, Dictionary.class);
        int pageNum=pageParam.getPageNum();
        int pageSize=pageParam.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        return this.dictionaryDao.queryAll(param);
    }

    /**
     * 新增数据
     *
     * @param dictionary 实例对象
     * @return 实例对象
     */
    @Override
    public Dictionary insert(Dictionary dictionary) {
        dictionary.setId(UUIDUtil.getuuid());
        this.dictionaryDao.insert(dictionary);
        return dictionary;
    }

    /**
     * 修改数据
     *
     * @param dictionary 实例对象
     * @return 实例对象
     */
    @Override
    public Dictionary update(Dictionary dictionary) {
        this.dictionaryDao.update(dictionary);
        return this.queryById(dictionary.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.dictionaryDao.deleteById(id) > 0;
    }
}
