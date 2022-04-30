package com.example.service;

import com.example.entity.Classes;
import java.util.List;

/**
 * (Classes)表服务接口
 *
 * @author wyl
 * @since 2021-10-09 11:02:58
 */
public interface ClassesService {

    /**
     * 通过ID查询单条数据
     *
     * @param classesId 主键
     * @return 实例对象
     */
    Classes findById(Integer classesId);

    /**
     * 查询所有
     *
     * @return 对象数组
     */
    List<Classes> findAll();

    /**
     * 新增数据
     *
     * @param classes 实例对象
     * @return 实例对象
     */
    int insert(Classes classes);

    /**
     * 修改数据
     *
     * @param classes 实例对象
     * @return 实例对象
     */
    int update(Classes classes);

    /**
     * 通过主键删除数据
     *
     * @param classesId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer classesId);

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
     * @param classesNum  类num
     * @param classesName 类名字
     * @return {@link List}<{@link Classes}>
     */
    List<Classes> findList(int currentPage, int pageSize, String classesNum, String classesName);

    /**
     * 添加时唯一性检查
     *
     * @param gradeId     年级id
     * @param classesName 类名字
     * @return {@link Classes}
     */
    Classes findByGradeIdAndClassesName(Integer gradeId, String classesName);

    /**
     * 修改时唯一性检查
     *
     * @param classesId   类id
     * @param gradeId     年级id
     * @param classesName 类名字
     * @return {@link Classes}
     */
    Classes findByIdGradeIdAndClassesName(Integer classesId, Integer gradeId, String classesName);

    /**
     * 根据所在年级查询班级 分页
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param gradeId     年级id
     * @param classesNum  类num
     * @param classesName 类名字
     * @return {@link List}<{@link Classes}>
     */
    List<Classes> findByGradeId(int currentPage, int pageSize, int gradeId, String classesNum, String classesName);

    /**
     * 分班
     *
     * @param ids       id
     * @param classesId 类id
     * @return int
     */
    int driver(int[] ids, int classesId);

    /**
     * 查询班级数量
     *
     * @return int
     */
    int findCount();

    /**
     * 查询教师所带班级
     *
     * @param teacherId 老师id
     * @return {@link List}<{@link Classes}>
     */
    List<Classes> findByTeacherId(int teacherId);
}
