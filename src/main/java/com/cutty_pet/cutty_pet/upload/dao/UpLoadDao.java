package com.cutty_pet.cutty_pet.upload.dao;

import com.cutty_pet.cutty_pet.upload.entity.FileDB;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (FileDB)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-03 18:35:31
 */
@Mapper
public interface UpLoadDao {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FileDB queryById(String id);
    /**
     * 新增数据
     *
     * @param FileDB 实例对象
     * @return 影响行数
     */
    int insert(FileDB FileDB);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);
}

