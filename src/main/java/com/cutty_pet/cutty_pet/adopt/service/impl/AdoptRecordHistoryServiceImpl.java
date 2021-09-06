package com.cutty_pet.cutty_pet.adopt.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cutty_pet.cutty_pet.adopt.entity.AdoptRecordHistory;
import com.cutty_pet.cutty_pet.adopt.dao.AdoptRecordHistoryDao;
import com.cutty_pet.cutty_pet.adopt.service.AdoptRecordHistoryService;
import com.cutty_pet.cutty_pet.common.OrderCreateNumber;
import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.common.UUIDUtil;
import com.cutty_pet.cutty_pet.dic.entity.Dictionary;
import com.cutty_pet.cutty_pet.pet.dao.PetStorageDao;
import com.cutty_pet.cutty_pet.pet.entity.PetStorage;
import com.cutty_pet.cutty_pet.user.entity.User;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 客户收养宠物记录表(AdoptRecordHistory)表服务实现类
 *
 * @author makejava
 * @since 2021-04-03 19:17:23
 */
@Service("adoptRecordHistoryServiceImpl")
public class AdoptRecordHistoryServiceImpl implements AdoptRecordHistoryService {
    @Resource
    private AdoptRecordHistoryDao adoptRecordHistoryDao;
    @Resource
    private PetStorageDao petStorageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AdoptRecordHistory queryById(String id) {
        return this.adoptRecordHistoryDao.queryById(id);
    }
    @Override
    public List<AdoptRecordHistory> queryByPage(PageParam pageParam) {
        JSONObject json = (JSONObject) JSON.toJSON(pageParam.getParam());
        AdoptRecordHistory param = (AdoptRecordHistory) JSON.toJavaObject(json, AdoptRecordHistory.class);
        int pageNum=pageParam.getPageNum();
        int pageSize=pageParam.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        return this.adoptRecordHistoryDao.queryAll(param);
    }
    @Override
    public void customerAdopt(AdoptRecordHistory adoptRecordHistory) throws Exception{
        adoptRecordHistory.setId(UUIDUtil.getuuid());
        adoptRecordHistory.setAdoptOrderId(OrderCreateNumber.createNumber());
        adoptRecordHistory.setAdoptTime(new Date());
        PetStorage petStorage =new PetStorage();
        petStorage.setPetCode(adoptRecordHistory.getPetCode());
        List<PetStorage> items =petStorageDao.queryAll(petStorage);
        if(items.size()>0){
            PetStorage item=items.get(0);
            if(item.getPetNumber()>0){
                item.setPetNumber(item.getPetNumber()-1);
                petStorageDao.update(item);
                adoptRecordHistoryDao.insert(adoptRecordHistory);
            }else{
                throw new RuntimeException();
            }
        }else{
            throw new RuntimeException();
        }
    }
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<AdoptRecordHistory> queryAllByLimit(int offset, int limit) {
        return this.adoptRecordHistoryDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param adoptRecordHistory 实例对象
     * @return 实例对象
     */
    @Override
    public AdoptRecordHistory insert(AdoptRecordHistory adoptRecordHistory) {
        adoptRecordHistory.setId(UUIDUtil.getuuid());
        this.adoptRecordHistoryDao.insert(adoptRecordHistory);
        return adoptRecordHistory;
    }

    /**
     * 修改数据
     *
     * @param adoptRecordHistory 实例对象
     * @return 实例对象
     */
    @Override
    public AdoptRecordHistory update(AdoptRecordHistory adoptRecordHistory) {
        this.adoptRecordHistoryDao.update(adoptRecordHistory);
        return this.queryById(adoptRecordHistory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.adoptRecordHistoryDao.deleteById(id) > 0;
    }
}
