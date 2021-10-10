package com.example.dao;

import com.example.entity.Professional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Professional)表数据库访问层
 *
 * @author wyl
 * @since 2021-10-07 18:02:09
 */
 @Mapper
public interface ProfessionalDao {

    /**
     * 通过ID查询单条数据
     *
     * @param professionalId 主键
     * @return 实例对象
     */
    Professional findById(Integer professionalId);

    /**
     * 查询所有
     *
     * @return 对象数组
     */
    List<Professional> findAll();

    /**
     * 新增数据
     *
     * @param professional 实例对象
     * @return 影响行数
     */
    int insert(Professional professional);

    /**
     * 修改数据
     *
     * @param professional 实例对象
     * @return 影响行数
     */
    int update(Professional professional);

    /**
     * 通过主键删除数据
     *
     * @param professionalId 主键
     * @return 影响行数
     */
    int deleteById(Integer professionalId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteBatch(int[] ids);

    /**
     * 分页查询
     * @param professionalNum
     * @param professionalName
     * @return
     */
    List<Professional> findList(String professionalNum, String professionalName);

    /**
     * 根据院系ID查询
     * @param collegeId
     * @return
     */
    List<Professional> findByCollegeId(int collegeId);

    /**
     * 添加时唯一性检查
     * @param collegeId
     * @param professionalName
     * @return
     */
    Professional findByCollegeIdAndProfessionalName(@Param("collegeId") int collegeId,
                                                    @Param("professionalName") String professionalName);

    /**
     * 修改时唯一性检查
     * @param professionalId
     * @param collegeId
     * @param professionalName
     * @return
     */
    Professional findByIdAndCollegeIdAndProfessionalName(@Param("professionalId") Integer professionalId,
                                                         @Param("collegeId") Integer collegeId,
                                                         @Param("professionalName") String professionalName);
}

