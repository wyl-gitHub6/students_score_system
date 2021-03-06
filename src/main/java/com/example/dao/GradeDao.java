package com.example.dao;

import com.example.entity.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Grade)表数据库访问层
 *
 * @author wyl
 * @since 2021-10-07 18:01:30
 */
 @Mapper
public interface GradeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param gradeId 主键
     * @return 实例对象
     */
    Grade findById(Integer gradeId);

    /**
     * 查询所有
     *
     * @return 对象数组
     */
    List<Grade> findAll();

    /**
     * 新增数据
     *
     * @param grade 实例对象
     * @return 影响行数
     */
    int insert(Grade grade);

    /**
     * 修改数据
     *
     * @param grade 实例对象
     * @return 影响行数
     */
    int update(Grade grade);

    /**
     * 通过主键删除数据
     *
     * @param gradeId 主键
     * @return 影响行数
     */
    int deleteById(Integer gradeId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteBatch(int[] ids);

    /**
     * 分页查询
     * @param gradeNum
     * @param gradeName
     * @return
     */
    List<Grade> findList(String gradeNum, String gradeName);

    /**
     * 根据ID查询
     * @param professionalId
     * @return
     */
    List<Grade> findByProfessionalId(int professionalId);

    /**
     * 添加时唯一性检查
     * @param professionalId
     * @param gradeName
     * @return
     */
    Grade findByProfessionalIdAndGradeName(@Param("professionalId") Integer professionalId,
                                           @Param("gradeName") String gradeName);

    /**
     * 修改时唯一性检查
     * @param gradeId
     * @param professionalId
     * @param gradeName
     * @return
     */
    Grade findByIdAndProfessionalIdAndGradeName(@Param("gradeId") Integer gradeId,
                                                @Param("professionalId") Integer professionalId,
                                                @Param("gradeName") String gradeName);

    /**
     * 查询年级数量
     * @return
     */
    int findCount();
}

