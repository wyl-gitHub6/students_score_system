package com.example.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.example.entity.Grade;
import com.example.dao.GradeDao;
import com.example.service.GradeService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import javax.annotation.Resource;

/**
 * (Grade)表服务实现类
 *
 * @author wyl
 * @since 2021-10-07 18:01:30
 */
@Service("gradeService")
@Transactional(rollbackFor = Exception.class)
public class GradeServiceImpl implements GradeService {
    @Resource
    private GradeDao gradeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param gradeId 主键
     * @return 实例对象
     */
    @Override
    public Grade findById(Integer gradeId) {
        return this.gradeDao.findById(gradeId);
    }

    /**
     * 查询所有数据
     *
     * @return 对象数组
     */
    @Override
    public List<Grade> findAll() {
        return this.gradeDao.findAll();
    }

    /**
     * 新增数据
     *
     * @param grade 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Grade grade) {
        grade.setGradeNum(RandomUtil.randomString(9));
        return this.gradeDao.insert(grade);
    }

    /**
     * 修改数据
     *
     * @param grade 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Grade grade) {
        return this.gradeDao.update(grade);
    }

    /**
     * 通过主键删除数据
     *
     * @param gradeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer gradeId) {
        return this.gradeDao.deleteById(gradeId) > 0;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public boolean deleteBatch(int[] ids) {
        return gradeDao.deleteBatch(ids);
    }

    @Override
    public List<Grade> findList(int currentPage, int pageSize, String gradeNum, String gradeName) {
        PageHelper.startPage(currentPage,pageSize);
        return this.gradeDao.findList(gradeNum,gradeName);
    }

    @Override
    public List<Grade> findByProfessionalId(int professionalId) {
        return gradeDao.findByProfessionalId(professionalId);
    }

    @Override
    public Grade findByProfessionalIdAndGradeName(Integer professionalId, String gradeName) {
        return gradeDao.findByProfessionalIdAndGradeName(professionalId,gradeName);
    }

    @Override
    public Grade findByIdAndProfessionalIdAndGradeName(Integer gradeId, Integer professionalId, String gradeName) {
        return gradeDao.findByIdAndProfessionalIdAndGradeName(gradeId,professionalId,gradeName);
    }

    @Override
    public int findCount() {
        return gradeDao.findCount();
    }
}
