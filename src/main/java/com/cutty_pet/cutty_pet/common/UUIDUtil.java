package com.cutty_pet.cutty_pet.common;

import java.util.UUID;

public class UUIDUtil {
    public static String getuuid(){

        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid;
    }

}
