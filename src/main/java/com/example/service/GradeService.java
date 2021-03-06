package com.example.service;

import com.example.entity.Grade;
import java.util.List;

/**
 * (Grade)表服务接口
 *
 * @author wyl
 * @since 2021-10-07 18:01:30
 */
public interface GradeService {

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
     * @return 实例对象
     */
    int insert(Grade grade);

    /**
     * 修改数据
     *
     * @param grade 实例对象
     * @return 实例对象
     */
    int update(Grade grade);

    /**
     * 通过主键删除数据
     *
     * @param gradeId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer gradeId);

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
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param gradeNum    年级num
     * @param gradeName   年级名字
     * @return {@link List}<{@link Grade}>
     */
    List<Grade> findList(int currentPage, int pageSize, String gradeNum, String gradeName);

    /**
     * 根据专业ID查询
     *
     * @param professionalId 专业id
     * @return {@link List}<{@link Grade}>
     */
    List<Grade> findByProfessionalId(int professionalId);

    /**
     * 添加时唯一性检查
     *
     * @param professionalId 专业id
     * @param gradeName      年级名字
     * @return {@link Grade}
     */
    Grade findByProfessionalIdAndGradeName(Integer professionalId, String gradeName);

    /**
     * 修改时唯一性检查
     *
     * @param gradeId        年级id
     * @param professionalId 专业id
     * @param gradeName      年级名字
     * @return {@link Grade}
     */
    Grade findByIdAndProfessionalIdAndGradeName(Integer gradeId, Integer professionalId, String gradeName);

    /**
     * 查询年级数量
     *
     * @return int
     */
    int findCount();
}
