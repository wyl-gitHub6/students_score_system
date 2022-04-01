package com.example.service;

import com.example.entity.Arrang;
import com.example.entity.Classes;

import java.util.List;

/**
 * @author wyl
 * @date 2022/03/22
 * @since 2021-10-13 22:58:58
 */
public interface ArrangService {

    /**
     * 查询所有
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param classesName 班级名称
     * @return {@link List}<{@link Classes}>
     */
    List<Classes> findClasses(int currentPage,int pageSize,String classesName);

    /**
     * 新增数据
     *
     * @param arrang 实例对象
     * @return 实例对象
     */
    int insert(Arrang arrang);


    /**
     * 安排必修课
     *
     * @param courseIdr 课程数组
     * @param classesId 班级id
     * @return {@link String}
     */
    String arrangCourse(int[] courseIdr, int classesId);

    /**
     * 根据班级ID查询
     *
     * @param classesId 班级id
     * @return {@link List}<{@link Classes}>
     */
    List<Classes> findByClassesId(int classesId);

    /**
     * 根据课程ID和班级ID删除
     *
     * @param courseId  课程id
     * @param classesId 班级id
     * @return boolean
     */
    boolean delete(int courseId, int classesId);

    /**
     * 根据课程ID查询
     *
     * @param courseId 课程id
     * @return {@link List}<{@link Classes}>
     */
    List<Classes> findByCourseId(int courseId);

}
