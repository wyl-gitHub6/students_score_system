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
     * 删除批处理
     *
     * @param ids id
     * @return boolean
     */
    boolean deleteBatch(int[] ids);

    /**
     * 登录
     *
     * @param userNum 用户num
     * @param pwd     密码
     * @return {@link User}
     */
    User login(String userNum, String pwd);

    /**
     * 分页查询
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param userName    用户名
     * @param userNum     用户num
     * @return {@link List}<{@link User}>
     */
    List<User> findList(int currentPage, int pageSize, String userName, String userNum);

    /**
     * 根据学生学号查询
     *
     * @param userNum 用户num
     * @return {@link User}
     */
    User findByUserNum(String userNum);

}
