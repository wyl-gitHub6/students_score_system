package com.example.service;

import com.example.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * (Student)表服务接口
 *
 * @author wyl
 * @since 2021-10-10 11:26:21
 */
public interface StudentService {

    /**
     * 通过ID查询单条数据
     *
     * @param studentId 主键
     * @return 实例对象
     */
    Student findById(Integer studentId);

    /**
     * 查询所有
     *
     * @return 对象数组
     */
    List<Student> findAll();

    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    int insert(Student student);

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    int update(Student student);

    /**
     * 通过主键删除数据
     *
     * @param studentId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer studentId);

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
     * @param studentNum  学生num
     * @param studentName 学生名字
     * @return {@link List}<{@link Student}>
     */
    List<Student> findList(int currentPage, int pageSize, String studentNum, String studentName);

    /**
     * 根据学号查询
     *
     * @param studentNum 学生num
     * @return {@link Student}
     */
    Student findByStudentNum(String studentNum);

    /**
     * 根据班级查询
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param classesId   类id
     * @param studentNum  学生num
     * @param studentName 学生名字
     * @return {@link List}<{@link Student}>
     */
    List<Student> findByClassesId(int currentPage, int pageSize, int classesId, String studentNum, String studentName);

    /**
     * 登录
     *
     * @param studentNum 学生num
     * @param password   密码
     * @return {@link Student}
     */
    Student login(String studentNum, String password);

    /**
     * 必修课录入成绩时查询该课程没成绩的学生
     *
     * @param classesId 类id
     * @param courseId  进程id
     * @return {@link List}<{@link Student}>
     */
    List<Student> findByClasses(int classesId,int courseId);

    /**
     * 查询学生数量
     *
     * @return int
     */
    int findCount();

    /**
     * 上传xls
     * 导入
     *
     * @param file 文件
     * @return int
     * @throws IOException ioexception
     */
    int uploadXls(MultipartFile file) throws IOException;
}
