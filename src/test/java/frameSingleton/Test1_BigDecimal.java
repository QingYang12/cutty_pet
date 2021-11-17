package frameSingleton;

import com.cutty_pet.cutty_pet.CuttyPetApplication;
import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.pet.dao.PetStorageDao;
import com.cutty_pet.cutty_pet.pet.dto.PetStorageDto;
import com.cutty_pet.cutty_pet.pet.entity.PetStorage;
import com.cutty_pet.cutty_pet.pet.service.impl.PetStorageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuttyPetApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Test1_BigDecimal {
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
        List arr=new ArrayList();
        when(petStorageDao.queryAll(any())).thenReturn(arr);
        List<PetStorageDto> resarr=petStorageServiceImpl.queryAll(petStorageVo);
        System.out.println(1);
    }
    @Test
    public  void test3() {
    }

    public  static Long revisePriceFun(Long number1,Double number2){
        Long res=null;
        BigDecimal num1=new BigDecimal(number1);
        BigDecimal num_1=num1.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);;
        BigDecimal num2=new BigDecimal(number2);
        BigDecimal v1=num2.divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP);
        BigDecimal v2=new BigDecimal(1).add(v1);
        BigDecimal v3=num_1.multiply(v2);
        BigDecimal v4=v3.setScale(2,BigDecimal.ROUND_HALF_UP);
        Long value=v4.multiply(new BigDecimal(100)).longValue();
        res=value;
        return res;
    }
    @Test
    void test2() {
        BigDecimal num1=new BigDecimal(2000);
        BigDecimal num11=num1.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);;
        BigDecimal num2=new BigDecimal(10.5);
        BigDecimal v1=num2.divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP);
        BigDecimal v2=new BigDecimal(1).add(v1);
        BigDecimal v3=num11.multiply(v2);
        BigDecimal v4=v3.setScale(2,BigDecimal.ROUND_HALF_UP);
        Long value=v4.multiply(new BigDecimal(100)).longValue();
        Long value2=revisePriceFun(2000l,10.5);


        System.out.println(value);
    }

    @Test
    void test4() {
       int i=10700%64;
        System.out.println(i);
    }
}
