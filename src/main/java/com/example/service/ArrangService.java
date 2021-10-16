package com.example.service;

import com.example.entity.Arrang;
import com.example.entity.Classes;

import java.util.List;

/**
 * (Arrang)表服务接口
 *
 * @author wyl
 * @since 2021-10-13 22:58:58
 */
public interface ArrangService {

    /**
     * 查询所有
     *
     * @return 对象数组
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
     * @param courseIdr
     * @param classesId
     * @return
     */
    String arrangCourse(int[] courseIdr, int classesId);

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
    boolean delete(int courseId, int classesId);
}
