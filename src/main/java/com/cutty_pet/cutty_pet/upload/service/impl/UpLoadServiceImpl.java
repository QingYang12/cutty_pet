package com.cutty_pet.cutty_pet.upload.service.impl;

import com.cutty_pet.cutty_pet.common.BASE64Util;
import com.cutty_pet.cutty_pet.common.UUIDUtil;
import com.cutty_pet.cutty_pet.upload.dao.UpLoadDao;
import com.cutty_pet.cutty_pet.upload.entity.FileDB;
import com.cutty_pet.cutty_pet.upload.service.UpLoadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * 系统字典表(FileDB)表服务实现类
 *
 * @author makejava
 * @since 2021-04-03 18:35:31
 */
@Service("upLoadServiceImpl")
public class UpLoadServiceImpl implements UpLoadService {
    @Resource
    private UpLoadDao upLoadDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public FileDB queryById(String id) {
        return this.upLoadDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param FileDB 实例对象
     * @return 实例对象
     */
    @Override
    public FileDB insert(FileDB FileDB) {
        FileDB.setId(UUIDUtil.getuuid());
        this.upLoadDao.insert(FileDB);
        return FileDB;
    }

    @Override
    public FileDB upLoad(MultipartFile file) throws Exception{
        FileDB fileDB=new FileDB();
        fileDB.setId(UUIDUtil.getuuid());
        String originalFilename=file.getOriginalFilename();
        String name=file.getName();
        String[] fileNameStrArr=originalFilename.split("\\.");
        fileDB.setName(fileNameStrArr[0]);
        fileDB.setType(fileNameStrArr[1]);
        String fileSrc64=BASE64Util.byte2Base64StringFun(file.getBytes());
        fileDB.setFileSrc(fileSrc64);
        this.upLoadDao.insert(fileDB);
        return fileDB;
    }
    /**
     * 通过主键下载文件
     *
     * @param id 主键
     * @return
     */
    @Override
    public FileDB downLoad(String id) {
        FileDB fileDB=this.upLoadDao.queryById(id);
        //byte[] filebyte=BASE64Util.base64String2ByteFun(fileDB.getFileSrc());
        //fileDB.setFilebyte(filebyte);
        return fileDB;
    }
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.upLoadDao.deleteById(id) > 0;
    }
}
