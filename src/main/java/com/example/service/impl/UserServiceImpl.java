package com.example.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.constant.MyConstant;
import com.example.entity.User;
import com.example.dao.UserDao;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import javax.annotation.Resource;

/**
 * (User)表服务实现类
 *
 * @author wyl
 * @since 2021-10-28 16:16:51
 */
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User findById(Integer userId) {
        return this.userDao.findById(userId);
    }

    @Override
    public List<User> findAll() {
        return this.userDao.findAll();
    }

    @Override
    public int insert(User user) {
        user.setUserPassword(SecureUtil.md5(MyConstant.DEFAULT_PASSWORD));
        user.setUserNum(RandomUtil.randomString(MyConstant.NUM_BIT));
        return this.userDao.insert(user);
    }

    @Override
    public int update(User user) {
        User u = userDao.findById(user.getUserId());
        if (!u.getUserPassword().equals(user.getUserPassword())){
            user.setUserPassword(SecureUtil.md5(user.getUserPassword()));
        }
        return this.userDao.update(user);
    }

    @Override
    public boolean deleteById(Integer userId) {
        return this.userDao.deleteById(userId) > 0;
    }

    @Override
    public boolean deleteBatch(int[] ids) {
        return userDao.deleteBatch(ids);
    }

    @Override
    public User login(String userNum, String pwd) {
        return userDao.findByUserNumAndPassword(userNum,pwd);
    }

    @Override
    public List<User> findList(int currentPage, int pageSize, String userName, String userNum) {
        PageHelper.startPage(currentPage,pageSize);
        return userDao.findList(userName,userNum);
    }

    @Override
    public User findByUserNum(String userNum) {
        return userDao.findByUserNum(userNum);
    }
}
