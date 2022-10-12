package com.cutty_pet.cutty_pet;

import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.pet.dao.PetStorageDao;
import com.cutty_pet.cutty_pet.pet.dto.PetStorageDto;
import com.cutty_pet.cutty_pet.pet.service.PetStorageService;
import com.cutty_pet.cutty_pet.pet.service.impl.PetStorageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)//MockitoJUnitRunner
@SpringBootTest(classes = CuttyPetApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CuttyPetApplicationTests {
    @InjectMocks
    PetStorageServiceImpl petStorageServiceImpl;
    @Mock
    PetStorageDao petStorageDao;
    @Test
    void contextLoads() {
    }



    @Test
    void test1() {
        PageParam petStorageVo=new PageParam();
        petStorageVo.setPageSize(10);
        petStorageVo.setPageNum(1);
        List<PetStorageDto> arr=petStorageServiceImpl.queryAll(petStorageVo);
        System.out.println(1);
    }
}
