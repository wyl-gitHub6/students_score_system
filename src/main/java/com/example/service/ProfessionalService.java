package com.example.service;

import com.example.entity.Professional;
import java.util.List;

/**
 * (Professional)表服务接口
 *
 * @author wyl
 * @since 2021-10-07 18:02:13
 */
public interface ProfessionalService {

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
     * @return 实例对象
     */
    int insert(Professional professional);

    /**
     * 修改数据
     *
     * @param professional 实例对象
     * @return 实例对象
     */
    int update(Professional professional);

    /**
     * 通过主键删除数据
     *
     * @param professionalId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer professionalId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteBatch(int[] ids);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param professionalNum
     * @param professionalName
     * @return
     */
    List<Professional> findList(int currentPage, int pageSize, String professionalNum, String professionalName);

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
    Professional findByCollegeIdAndProfessionalName(int collegeId, String professionalName);

    /**
     * 修改时唯一性检查
     * @param professionalId
     * @param collegeId
     * @param professionalName
     * @return
     */
    Professional findByIdAndCollegeIdAndProfessionalName(Integer professionalId, Integer collegeId, String professionalName);
}
