package com.cutty_pet.cutty_pet.upload.service;

import com.cutty_pet.cutty_pet.upload.entity.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 系统字典表(FileDB)表服务接口
 *
 * @author makejava
 * @since 2021-04-03 18:35:31
 */

public interface UpLoadService {

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
     * @return 实例对象
     */
    FileDB insert(FileDB FileDB);

    FileDB upLoad(MultipartFile file)  throws  Exception;
    FileDB downLoad(String id)  throws  Exception;
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);
}
