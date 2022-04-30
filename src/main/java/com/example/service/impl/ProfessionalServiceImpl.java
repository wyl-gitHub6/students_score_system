package com.example.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.example.constant.MyConstant;
import com.example.entity.Professional;
import com.example.dao.ProfessionalDao;
import com.example.service.ProfessionalService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import javax.annotation.Resource;

/**
 * (Professional)表服务实现类
 *
 * @author wyl
 * @since 2021-10-07 18:02:13
 */
@Service("professionalService")
@Transactional(rollbackFor = Exception.class)
public class ProfessionalServiceImpl implements ProfessionalService {
    @Resource
    private ProfessionalDao professionalDao;

    @Override
    public Professional findById(Integer professionalId) {
        return this.professionalDao.findById(professionalId);
    }

    @Override
    public List<Professional> findAll() {
        return this.professionalDao.findAll();
    }

    @Override
    public int insert(Professional professional) {
        professional.setProfessionalNum(RandomUtil.randomString(MyConstant.NUM_BIT));
        String maxCode = professionalDao.findMaxCode();
        professional.setProfessionalCode(null == maxCode || maxCode.equals(MyConstant.ONE_STR)?MyConstant.DEFAULT_CODE:maxCode.substring(0,maxCode.lastIndexOf(".")));
        return this.professionalDao.insert(professional);
    }

    @Override
    public int update(Professional professional) {
        return this.professionalDao.update(professional);
    }

    @Override
    public boolean deleteById(Integer professionalId) {
        return this.professionalDao.deleteById(professionalId) > 0;
    }

    @Override
    public boolean deleteBatch(int[] ids) {
        return professionalDao.deleteBatch(ids);
    }

    @Override
    public List<Professional> findList(int currentPage, int pageSize, String professionalNum, String professionalName) {
        PageHelper.startPage(currentPage,pageSize);
        return this.professionalDao.findList(professionalNum,professionalName);
    }

    @Override
    public List<Professional> findByCollegeId(int collegeId) {
        return professionalDao.findByCollegeId(collegeId);
    }

    @Override
    public Professional findByCollegeIdAndProfessionalName(int collegeId, String professionalName) {
        return professionalDao.findByCollegeIdAndProfessionalName(collegeId,professionalName);
    }

    @Override
    public Professional findByIdAndCollegeIdAndProfessionalName(Integer professionalId, Integer collegeId, String professionalName) {
        return professionalDao.findByIdAndCollegeIdAndProfessionalName(professionalId,collegeId,professionalName);
    }

    @Override
    public int findCount() {
        return professionalDao.findCount();
    }
}
