package com.example.controller;

import cn.hutool.crypto.SecureUtil;
import com.example.constant.MyConstant;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.utils.Result;
import com.example.config.SendEmailConfig;
import com.example.utils.VerCode;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户
 *
 * @author wyl
 * @since 2021-10-28 16:16:51
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private SendEmailConfig sendEmailConfig;

    /**
     * 查询用户列表
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param userName    用户名
     * @param userNum     用户编号
     * @return {@link Result}<{@link PageInfo}<{@link User}>>
     */
    @GetMapping("/findList")
    public Result<PageInfo<User>> findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "8") int pageSize,
                           @RequestParam(value = "userName",defaultValue = "") String userName,
                           @RequestParam(value = "userNum",defaultValue = "") String userNum){
        List<User> list = userService.findList(currentPage, pageSize, userName, userNum);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo, MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param userId 主键
     * @return 单条数据
     */
    @GetMapping("/findById")
    public Result<User> findById(@RequestParam("userId") int userId) {
        return Result.success(this.userService.findById(userId),MyConstant.RES_SUCCESS_MESSAGE);
    }

     /**
     * 查询所有数据
     *
     * @return 数据数组
     */
    @GetMapping("/findAll")
    public Result<List<User>> findAll() {
        return Result.success(this.userService.findAll(),MyConstant.RES_SUCCESS_MESSAGE);
    }

    /**
     * 新增数据
     *
     * @param user 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public Result<String> insert(@RequestBody User user) {
        int i = userService.insert(user);
        if (i > 0){
            return Result.success(MyConstant.RES_INSERT_SUCCESS);
        }
        return Result.error(MyConstant.RES_INSERT_FAILED);
    }

    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public Result<String> update(@RequestBody User user) {
        int i = userService.update(user);
        if (i > 0){
            return Result.success(MyConstant.RES_UPDATE_SUCCESS);
        }
        return Result.error(MyConstant.RES_UPDATE_FAILED);
    }

    /**
     * 删除数据
     *
     * @param userId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public Result<String> deleteById(@RequestParam("userId") int userId) {
        boolean i = userService.deleteById(userId);
        if (i){
            return Result.success(MyConstant.RES_DELETE_SUCCESS);
        }
        return Result.error(MyConstant.RES_DELETE_FAILED);
    }

    /**
     * 删除批处理
     *
     * @param ids id
     * @return {@link Result}
     */
    @DeleteMapping("deleteBatch")
    public Result<String> deleteBatch(@RequestParam("ids") int[] ids){
        boolean i = userService.deleteBatch(ids);
        if (i){
            return Result.success(MyConstant.RES_DELETE_SUCCESS);
        }
        return Result.error(MyConstant.RES_DELETE_FAILED);
    }

    /**
     * 登录
     *
     * @param userNum  用户num
     * @param password 密码
     * @return {@link Result}
     */
    @GetMapping("/login")
    public Result<User> login(@RequestParam("userNum") String userNum,
                        @RequestParam("password") String password){
        String pwd = SecureUtil.md5(password);
        User login = userService.login(userNum, pwd);
        if(null != login){
            return Result.success(login,"登录成功!");
        }
        return Result.error("用户名或密码错误!");
    }

    /**
     * 发送电子邮件
     *
     * @param userNum      用户num
     * @param emailAddress 电子邮件地址
     * @return {@link Result}
     */
    @GetMapping("/sendEmail")
    public Result<User> sendEmail(@RequestParam("userNum") String userNum,
                            @RequestParam("emailAddress") String emailAddress){
        User user = userService.findByUserNum(userNum);
        if (null == user){
            return Result.error("请输入正确的管理员编号！");
        }
        if (!user.getUserEmail().equals(emailAddress)){
            return Result.error("请输入绑定的邮箱！");
        }
        String code = VerCode.getVerCode();
        sendEmailConfig.sendEmail(emailAddress,code);
        user.setCode(code);
        return Result.success(user,"发送成功,注意查收！");
    }

    /**
     * 修改密码判断与旧密码加密后是否相同
     *
     * @param userId   用户id
     * @param password 密码
     * @return {@link Result}
     */
    @GetMapping("/updatePassword")
    public Result<String> updatePassword(@RequestParam("userId") int userId,
                                 @RequestParam("password") String password){
        User user = userService.findById(userId);
        if (user.getUserPassword().equals(SecureUtil.md5(password))){
            return Result.success("与旧密码相同");
        }
        return Result.error("密码不同");
    }
}

