package com.example.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.example.constant.MyConstant;
import com.example.entity.College;
import com.example.dao.CollegeDao;
import com.example.service.CollegeService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import javax.annotation.Resource;

/**
 * (College)表服务实现类
 *
 * @author wyl
 * @since 2021-10-07 18:01:30
 */
@Service("collegeService")
@Transactional(rollbackFor = Exception.class)
public class CollegeServiceImpl implements CollegeService {
    @Resource
    private CollegeDao collegeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param collegeId 主键
     * @return 实例对象
     */
    @Override
    public College findById(Integer collegeId) {
        return this.collegeDao.findById(collegeId);
    }

    /**
     * 查询所有数据
     *
     * @return 对象数组
     */
    @Override
    public List<College> findAll() {
        return this.collegeDao.findAll();
    }

    /**
     * 新增数据
     *
     * @param college 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(College college) {
        college.setCollegeNum(RandomUtil.randomString(MyConstant.NUM_BIT));
        String maxCode = collegeDao.findMaxCode();
        college.setCollegeCode(null == maxCode || maxCode.equals(MyConstant.ONE_STR)?MyConstant.DEFAULT_CODE:maxCode);
        return this.collegeDao.insert(college);
    }

    /**
     * 修改数据
     *
     * @param college 实例对象
     * @return 实例对象
     */
    @Override
    public int update(College college) {
        return this.collegeDao.update(college);
    }

    /**
     * 通过主键删除数据
     *
     * @param collegeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer collegeId) {
        return this.collegeDao.deleteById(collegeId) > 0;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public boolean deleteBatch(int[] ids) {
        return collegeDao.deleteBatch(ids);
    }

    @Override
    public List<College> findList(int currentPage, int pageSize, String collegeNum, String collegeName) {
        PageHelper.startPage(currentPage,pageSize);
        return collegeDao.findList(collegeNum,collegeName);
    }

    @Override
    public int findCount() {
        return collegeDao.findCount();
    }
}
