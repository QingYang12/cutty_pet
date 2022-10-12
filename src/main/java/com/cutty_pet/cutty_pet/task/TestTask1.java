package com.cutty_pet.cutty_pet.task;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.xxl.job.core.context.XxlJobHelper;
import java.util.concurrent.TimeUnit;

@Component
public class TestTask1 {
    private static Logger logger = LoggerFactory.getLogger(TestTask1.class);
    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("testTask1")
    public void testTask1fun() throws Exception {
        XxlJobHelper.log("XXL-JOB, Hello World.");
        System.out.println("xxxxxxxx test testTask1 start xxxxxxx");
        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        System.out.println("xxxxxxxx test testTask1 end xxxxxxx");
        // default success
    }
}
