package com.cutty_pet.cutty_pet.devops.controller;

import com.cutty_pet.cutty_pet.devops.entity.DEVOPSEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("devOps")
public class DevOpsController {

    @GetMapping("test")
    public DEVOPSEntity selectOne() {
        DEVOPSEntity devOPSEntity = new DEVOPSEntity();
        return devOPSEntity;
    }

}
