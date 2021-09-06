package com.cutty_pet.cutty_pet.succour.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cutty_pet.cutty_pet.adopt.entity.AdoptRecordHistory;
import com.cutty_pet.cutty_pet.common.OrderCreateNumber;
import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.common.UUIDUtil;
import com.cutty_pet.cutty_pet.pet.dao.PetStorageDao;
import com.cutty_pet.cutty_pet.pet.entity.PetStorage;
import com.cutty_pet.cutty_pet.pet.vo.PetStorageVo;
import com.cutty_pet.cutty_pet.succour.entity.SuccourRecordHistory;
import com.cutty_pet.cutty_pet.succour.dao.SuccourRecordHistoryDao;
import com.cutty_pet.cutty_pet.succour.service.SuccourRecordHistoryService;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 站点救助宠物记录表(SuccourRecordHistory)表服务实现类
 *
 * @author makejava
 * @since 2021-04-03 19:17:37
 */
@Service("succourRecordHistoryServiceImpl")
public class SuccourRecordHistoryServiceImpl implements SuccourRecordHistoryService {
    @Resource
    private SuccourRecordHistoryDao succourRecordHistoryDao;
    @Resource
    private PetStorageDao petStorageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SuccourRecordHistory queryById(String id) {
        return this.succourRecordHistoryDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SuccourRecordHistory> queryAllByLimit(int offset, int limit) {
        return this.succourRecordHistoryDao.queryAllByLimit(offset, limit);
    }
    @Override
    public void sitSuccour(SuccourRecordHistory succourRecordHistory) {
        succourRecordHistory.setId(UUIDUtil.getuuid());
        succourRecordHistory.setSuccourOrderId(OrderCreateNumber.createNumber());//生成救助单号
        succourRecordHistoryDao.insert(succourRecordHistory);
        PetStorage petStorage =new PetStorage();
        petStorage.setPetCode(succourRecordHistory.getPetCode());
        List<PetStorage> items =petStorageDao.queryAll(petStorage);
        if(items.size()>0){
            PetStorage item=items.get(0);
            item.setPetNumber(item.getPetNumber()+1);
            petStorageDao.update(item);
        }else{
            PetStorage item =new PetStorage();
            item.setPetNumber(1);
            item.setPetCode(succourRecordHistory.getPetCode());
            item.setId(UUIDUtil.getuuid());
            petStorageDao.insert(item);
        }
    }
    @Override
    public List<SuccourRecordHistory> queryByPage(PageParam pageParam) {
        JSONObject json = (JSONObject) JSON.toJSON(pageParam.getParam());
        SuccourRecordHistory param = (SuccourRecordHistory) JSON.toJavaObject(json, SuccourRecordHistory.class);
        int pageNum=pageParam.getPageNum();
        int pageSize=pageParam.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        return this.succourRecordHistoryDao.queryAll(param);
    }
    /**
     * 新增数据
     *
     * @param succourRecordHistory 实例对象
     * @return 实例对象
     */
    @Override
    public SuccourRecordHistory insert(SuccourRecordHistory succourRecordHistory) {
        succourRecordHistory.setId(UUIDUtil.getuuid());
        this.succourRecordHistoryDao.insert(succourRecordHistory);
        return succourRecordHistory;
    }

    /**
     * 修改数据
     *
     * @param succourRecordHistory 实例对象
     * @return 实例对象
     */
    @Override
    public SuccourRecordHistory update(SuccourRecordHistory succourRecordHistory) {
        this.succourRecordHistoryDao.update(succourRecordHistory);
        return this.queryById(succourRecordHistory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.succourRecordHistoryDao.deleteById(id) > 0;
    }



}
