package com.example.dao;

import com.example.entity.College;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * (College)表数据库访问层
 *
 * @author wyl
 * @since 2021-10-07 18:01:30
 */
 @Mapper
public interface CollegeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param collegeId 主键
     * @return 实例对象
     */
    College findById(Integer collegeId);

    /**
     * 查询所有
     *
     * @return 对象数组
     */
    List<College> findAll();

    /**
     * 新增数据
     *
     * @param college 实例对象
     * @return 影响行数
     */
    int insert(College college);

    /**
     * 修改数据
     *
     * @param college 实例对象
     * @return 影响行数
     */
    int update(College college);

    /**
     * 通过主键删除数据
     *
     * @param collegeId 主键
     * @return 影响行数
     */
    int deleteById(Integer collegeId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteBatch(int[] ids);

    /**
     * 分页查询
     * @param collegeNum
     * @param collegeName
     * @return
     */
    List<College> findList(String collegeNum, String collegeName);

    /**
     * 查询院系数量
     * @return
     */
    int findCount();
}

