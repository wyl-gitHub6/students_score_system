package com.example.service;

import com.example.entity.Teacher;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * (Teacher)表服务接口
 *
 * @author wyl
 * @since 2021-10-06 18:43:28
 */
public interface TeacherService {

    /**
     * 通过ID查询单条数据
     *
     * @param teacherId 主键
     * @return 实例对象
     */
    Teacher findById(Integer teacherId);

    /**
     * 查询所有
     *
     * @return 对象数组
     */
    List<Teacher> findAll();

    /**
     * 新增数据
     *
     * @param teacher 实例对象
     * @return 实例对象
     */
    int insert(Teacher teacher);

    /**
     * 修改数据
     *
     * @param teacher 实例对象
     * @return 实例对象
     */
    int update(Teacher teacher);

    /**
     * 通过主键删除数据
     *
     * @param teacherId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer teacherId);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param teacherNum
     * @param teacherName
     * @return
     */
    List<Teacher> findList(int currentPage, int pageSize, String teacherNum, String teacherName);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteBatch(int[] ids);

    /**
     * 登录
     * @param teacherNum
     * @param password
     * @return
     */
    Teacher login(String teacherNum, String password);

    /**
     * 查询教师数量
     * @return
     */
    int findCount();

    /**
     * 根据职工编号查询
     * @param teacherNum
     * @return
     */
    Teacher findByNum(String teacherNum);

    /**
     * 导入
     * @param file
     * @return
     * @throws IOException
     */
    int uploadXls(MultipartFile file) throws IOException;
}
