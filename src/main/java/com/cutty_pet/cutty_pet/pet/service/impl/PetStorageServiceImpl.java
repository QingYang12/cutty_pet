package com.cutty_pet.cutty_pet.pet.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.common.UUIDUtil;
import com.cutty_pet.cutty_pet.dic.entity.Dictionary;
import com.cutty_pet.cutty_pet.pet.dto.PetStorageDto;
import com.cutty_pet.cutty_pet.pet.entity.PetStorage;
import com.cutty_pet.cutty_pet.pet.dao.PetStorageDao;
import com.cutty_pet.cutty_pet.pet.service.PetStorageService;
import com.cutty_pet.cutty_pet.pet.vo.PetStorageVo;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 宠物库存表
 * 库中现有宠物(PetStorage)表服务实现类
 *
 * @author makejava
 * @since 2021-04-03 18:35:57
 */
@Service("petStorageServiceImpl")
public class PetStorageServiceImpl implements PetStorageService {
    @Resource
    private PetStorageDao petStorageDao;

    /**
     * 主页搜索宠物接口 API001
     *
     * @param
     * @return 实例对象
     */
    @Override
    public List<PetStorageDto> searchPet(PageParam pageParam) {
        JSONObject json = (JSONObject) JSON.toJSON(pageParam.getParam());
        PetStorageVo param = (PetStorageVo) JSON.toJavaObject(json, PetStorageVo.class);
        int pageNum=pageParam.getPageNum();
        int pageSize=pageParam.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        return this.petStorageDao.searchPet(param);
    }
    @Override
    public List<PetStorageDto> queryAll(PageParam pageParam) {
        JSONObject json = (JSONObject) JSON.toJSON(pageParam.getParam());
        PetStorageVo param = (PetStorageVo) JSON.toJavaObject(json, PetStorageVo.class);
        int pageNum=pageParam.getPageNum();
        int pageSize=pageParam.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        PetStorage a=new PetStorage();
        this.petStorageDao.queryAll(a);
        return null;
    }
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PetStorage queryById(String id) {
        return this.petStorageDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<PetStorage> queryAllByLimit(int offset, int limit) {
        return this.petStorageDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param petStorage 实例对象
     * @return 实例对象
     */
    @Override
    public PetStorage insert(PetStorage petStorage) {
        petStorage.setId(UUIDUtil.getuuid());
        this.petStorageDao.insert(petStorage);
        return petStorage;
    }

    /**
     * 修改数据
     *
     * @param petStorage 实例对象
     * @return 实例对象
     */
    @Override
    public PetStorage update(PetStorage petStorage) {
        this.petStorageDao.update(petStorage);
        return this.queryById(petStorage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.petStorageDao.deleteById(id) > 0;
    }



}
