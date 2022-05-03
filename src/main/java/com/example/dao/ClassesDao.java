package com.example.dao;

import com.example.entity.Classes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
     * 删除批处理
     * 批量删除
     *
     * @param ids id
     * @return boolean
     */
    boolean deleteBatch(int[] ids);

    /**
     * 查询列表
     * 分页查询
     *
     * @param classesNum  类num
     * @param classesName 类名字
     * @return {@link List}<{@link Classes}>
     */
    List<Classes> findList(@Param("classesNum") String classesNum,
                           @Param("classesName") String classesName);

    /**
     * 查询通过年级id和类名字
     * 添加时唯一性检查
     *
     * @param gradeId     年级id
     * @param classesName 类名字
     * @return {@link Classes}
     */
    Classes findByGradeIdAndClassesName(@Param("gradeId") Integer gradeId,
                                        @Param("classesName")String classesName);

    /**
     * 查询通过id年级id和类名字
     * 修改时唯一性检查
     *
     * @param classesId   类id
     * @param gradeId     年级id
     * @param classesName 类名字
     * @return {@link Classes}
     */
    Classes findByIdGradeIdAndClassesName(@Param("classesId") Integer classesId,
                                          @Param("gradeId") Integer gradeId,
                                          @Param("classesName") String classesName);

    /**
     * 查询通过年级id列表
     *
     * @param gradeId     年级id
     * @param classesNum  类num
     * @param classesName 类名字
     * @return {@link List}<{@link Classes}>
     */
    List<Classes> findByGradeIdList(@Param("gradeId") int gradeId,
                                @Param("classesNum") String classesNum,
                                @Param("classesName") String classesName);

    /**
     * 查询数
     * 查询班级数量
     *
     * @return int
     */
    int findCount();

    /**
     * 查询通过老师id
     * 查询教师所带班级
     *
     * @param teacherId 老师id
     * @return {@link List}<{@link Classes}>
     */
    List<Classes> findByTeacherId(int teacherId);

    /**
     * 查询马克斯代码
     * 查询最大编码
     *
     * @return {@link String}
     */
    String findMaxCode();

    /**
     * 查询代码
     * 查询编码
     *
     * @param classesId 类id
     * @return {@link Map}<{@link String}, {@link String}>
     */
    Map<String,String> findCode(int classesId);

    /**
     * 查询通过年级id
     *
     * @param gradeId 年级id
     * @return {@link Classes}
     */
    List<Classes> findByGradeId(int gradeId);
}

