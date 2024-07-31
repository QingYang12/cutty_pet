package com.cutty_pet.cutty_pet.embedded.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.common.UUIDUtil;
import com.cutty_pet.cutty_pet.embedded.entity.Embedded;
import com.cutty_pet.cutty_pet.embedded.service.EmbeddedService;
import com.cutty_pet.cutty_pet.pet.dto.PetStorageDto;
import com.cutty_pet.cutty_pet.pet.entity.PetStorage;
import com.cutty_pet.cutty_pet.pet.dao.PetStorageDao;
import com.cutty_pet.cutty_pet.pet.service.PetStorageService;
import com.cutty_pet.cutty_pet.pet.vo.PetStorageVo;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**

 *
 * @author makejava
 * @since 2021-04-03 18:35:57
 */
@Service("embeddedServiceImpl")
public class EmbeddedServiceImpl implements EmbeddedService {


    @Override
    public void handreceive(Embedded embeddedvo) {
        System.out.println(1);
        System.out.println(JSONObject.toJSON(embeddedvo));
    }
}
