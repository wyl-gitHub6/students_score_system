package com.example.service;

import com.example.entity.User;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author wyl
 * @since 2021-10-28 16:16:51
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    User findById(Integer userId);

    /**
     * 查询所有
     *
     * @return 对象数组
     */
    List<User> findAll();

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer userId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteBatch(int[] ids);

    /**
     * 登录
     * @param userNum
     * @param pwd
     * @return
     */
    User login(String userNum, String pwd);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param userName
     * @param userNum
     * @return
     */
    List<User> findList(int currentPage, int pageSize, String userName, String userNum);

    /**
     * 根据学生学号查询
     * @param userNum
     * @return
     */
    User findByUserNum(String userNum);
}
