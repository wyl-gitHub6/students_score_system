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
     * 删除批处理
     *
     * @param ids id
     * @return boolean
     */
    boolean deleteBatch(int[] ids);

    /**
     * 分页查询
     *
     * @param currentPage      当前页面
     * @param pageSize         页面大小
     * @param professionalNum  专业num
     * @param professionalName 专业名称
     * @return {@link List}<{@link Professional}>
     */
    List<Professional> findList(int currentPage, int pageSize, String professionalNum, String professionalName);

    /**
     * 根据院系ID查询
     *
     * @param collegeId 大学id
     * @return {@link List}<{@link Professional}>
     */
    List<Professional> findByCollegeId(int collegeId);

    /**
     * 添加时唯一性检查
     *
     * @param collegeId        大学id
     * @param professionalName 专业名称
     * @return {@link Professional}
     */
    Professional findByCollegeIdAndProfessionalName(int collegeId, String professionalName);

    /**
     * 修改时唯一性检查
     *
     * @param professionalId   专业id
     * @param collegeId        大学id
     * @param professionalName 专业名称
     * @return {@link Professional}
     */
    Professional findByIdAndCollegeIdAndProfessionalName(Integer professionalId, Integer collegeId, String professionalName);

    /**
     * 查询专业数量
     *
     * @return int
     */
    int findCount();
}
