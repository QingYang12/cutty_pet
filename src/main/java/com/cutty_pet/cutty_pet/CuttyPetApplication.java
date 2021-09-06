package com.cutty_pet.cutty_pet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cutty_pet.*")
public class CuttyPetApplication {

    public static void main(String[] args) {
        SpringApplication.run(CuttyPetApplication.class, args);
    }

}
