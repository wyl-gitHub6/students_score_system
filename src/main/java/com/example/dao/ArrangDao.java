package com.example.dao;

import com.example.entity.Arrang;
import com.example.entity.Classes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Arrang)表数据库访问层
 *
 * @author wyl
 * @since 2021-10-13 22:58:58
 */
 @Mapper
public interface ArrangDao {


    /**
     * 查询班级
     *
     * @param classesName 班级名称
     * @return {@link List}<{@link Classes}>
     */
    List<Classes> findClasses(String classesName);

    /**
     * 新增数据
     *
     * @param arrang 实例对象
     * @return 影响行数
     */
    int insert(Arrang arrang);

    /**
     * 根据课程ID和班级ID查询
     *
     * @param courseId  课程id
     * @param classesId 班级id
     * @return {@link Arrang}
     */
    Arrang findByCourseIdAndClassesId(@Param("courseId") int courseId,
                                      @Param("classesId") int classesId);

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
    boolean delete(@Param("courseId") int courseId,
                   @Param("classesId") int classesId);

    /**
     * 根据课程ID查询
     *
     * @param courseId 课程id
     * @return {@link List}<{@link Classes}>
     */
    List<Classes> findByCourseId(int courseId);
}

