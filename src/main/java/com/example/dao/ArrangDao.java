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
     * @param classesName
     * @return
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
     * @param courseId
     * @param classesId
     * @return
     */
    Arrang findByCourseIdAndClassesId(@Param("courseId") int courseId,
                                      @Param("classesId") int classesId);

    /**
     * 根据班级ID查询
     * @param classesId
     * @return
     */
    List<Classes> findByClassesId(int classesId);

    /**
     * 删除
     * @param courseId
     * @param classesId
     * @return
     */
    boolean delete(@Param("courseId") int courseId,
                   @Param("classesId") int classesId);
}

