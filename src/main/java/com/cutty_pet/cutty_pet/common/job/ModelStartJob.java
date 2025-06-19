package com.cutty_pet.cutty_pet.common.job;

import com.cutty_pet.cutty_pet.common.cache.ModelCacheManager;
import com.cutty_pet.cutty_pet.devops.service.impl.OminiHandVsionModelShell;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 模型定时调度使用
 */
@Component
public class ModelStartJob {
    @Scheduled(fixedRate = 500000)
    public void runModelJobCheck(){
        Boolean handVisionModelKey=ModelCacheManager.getInstance().get("handVisionModelKey",Boolean.class);
        if(handVisionModelKey!=null&&handVisionModelKey==true){
            //System.out.println("handVisionModel start");
            //OminiHandVsionModelShell.main(new String[]{});
        }else{
           //OminiHandVsionModelShell.main(new String[]{});
        }

    }
}
