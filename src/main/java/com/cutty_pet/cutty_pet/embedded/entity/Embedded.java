package com.cutty_pet.cutty_pet.embedded.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;
import java.util.Map;

/**
 * 宠物库存表
 * 库中现有宠物(PetStorage)实体类
 *
 * @author makejava
 * @since 2021-04-03 18:35:56
 */
@Data
public class Embedded implements Serializable {

    Map<String, String[]> parameterMap;
}
