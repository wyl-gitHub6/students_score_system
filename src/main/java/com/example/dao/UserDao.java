package com.example.dao;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author wyl
 * @since 2021-10-28 16:16:51
 */
 @Mapper
public interface UserDao {

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
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Integer userId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteBatch(int[] ids);

    /**
     * 登录
     * @param userNum
     * @param password
     * @return
     */
    User findByUserNumAndPassword(@Param("userNum") String userNum,
                                   @Param("password") String password);

    /**
     * 分页查询
     * @param userName
     * @param userNum
     * @return
     */
    List<User> findList(@Param("userName") String userName,
                        @Param("userNum") String userNum);

    /**
     * 根据学生学号查询
     * @param userNum
     * @return
     */
    User findByUserNum(String userNum);
}

