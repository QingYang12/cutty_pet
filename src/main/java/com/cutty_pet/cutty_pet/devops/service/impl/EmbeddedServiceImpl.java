package com.cutty_pet.cutty_pet.devops.service.impl;

import com.cutty_pet.cutty_pet.devops.service.EmbeddedService;
import org.springframework.stereotype.Service;

/**
 * 客户收养宠物记录表(AdoptRecordHistory)表服务实现类
 *
 * @author makejava
 * @since 2021-04-03 19:17:23
 */
@Service("embeddedServiceImpl")
public class EmbeddedServiceImpl implements EmbeddedService {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public void queryById(String id) {
    }
}
