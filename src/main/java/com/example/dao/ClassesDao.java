package com.example.dao;

import com.example.entity.Classes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Classes)表数据库访问层
 *
 * @author wyl
 * @since 2021-10-09 11:02:58
 */
 @Mapper
public interface ClassesDao {

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
     * @return 影响行数
     */
    int insert(Classes classes);

    /**
     * 修改数据
     *
     * @param classes 实例对象
     * @return 影响行数
     */
    int update(Classes classes);

    /**
     * 通过主键删除数据
     *
     * @param classesId 主键
     * @return 影响行数
     */
    int deleteById(Integer classesId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteBatch(int[] ids);

    /**
     * 分页查询
     * @param classesNum
     * @param classesName
     * @return
     */
    List<Classes> findList(@Param("classesNum") String classesNum,
                           @Param("classesName") String classesName);

    /**
     * 添加时唯一性检查
     * @param gradeId
     * @param classesName
     * @return
     */
    Classes findByGradeIdAndClassesName(@Param("gradeId") Integer gradeId,
                                        @Param("classesName")String classesName);

    /**
     * 修改时唯一性检查
     * @param classesId
     * @param gradeId
     * @param classesName
     * @return
     */
    Classes findByIdGradeIdAndClassesName(@Param("classesId") Integer classesId,
                                          @Param("gradeId") Integer gradeId,
                                          @Param("classesName") String classesName);

    /**
     * 根据年级查询
     * @param gradeId
     * @param classesNum
     * @param classesName
     * @return
     */
    List<Classes> findByGradeId(@Param("gradeId") int gradeId,
                                @Param("classesNum") String classesNum,
                                @Param("classesName") String classesName);
}

