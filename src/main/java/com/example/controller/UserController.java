package com.example.controller;

import cn.hutool.crypto.SecureUtil;
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
 * (User)表控制层
 *
 * @author wyl
 * @since 2021-10-28 16:16:51
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                           @RequestParam(value = "userName",defaultValue = "") String userName,
                           @RequestParam(value = "userNum",defaultValue = "") String userNum){
        List<User> list = userService.findList(currentPage, pageSize, userName, userNum);
        PageInfo pageInfo = new PageInfo(list);
        return Result.success(pageInfo,"查询成功!");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param userId 主键
     * @return 单条数据
     */
    @GetMapping("/findById")
    public Result findById(@RequestParam("userId") int userId) {
        return Result.success(this.userService.findById(userId),"查询成功!");
    }

     /**
     * 查询所有数据
     *
     * @return 数据数组
     */
    @GetMapping("/findAll")
    public Result findAll() {
        return Result.success(this.userService.findAll(),"查询成功!");
    }

    /**
     * 新增数据
     *
     * @param user 实体
     * @return 新增结果
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody User user) {
        int i = userService.insert(user);
        if (i > 0){
            return Result.success("添加成功!");
        }
        return Result.error("添加失败!");
    }

    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        int i = userService.update(user);
        if (i > 0){
            return Result.success("编辑成功!");
        }
        return Result.error("编辑失败!");
    }

    /**
     * 删除数据
     *
     * @param userId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("userId") int userId) {
        boolean i = userService.deleteById(userId);
        if (i == true){
            return Result.success("删除成功!");
        }
        return Result.error("删除失败!");
    }

     /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestParam("ids") int[] ids){
        boolean i = userService.deleteBatch(ids);
        if (i == true){
            return Result.success("删除成功!");
        }
        return Result.error("删除失败!");
    }

    /**
     * 登录
     * @param userNum
     * @param password
     * @return
     */
    @GetMapping("/login")
    public Result login(@RequestParam("userNum") String userNum,
                        @RequestParam("password") String password){
        String pwd = SecureUtil.md5(password);
        User login = userService.login(userNum, pwd);
        if(null != login){
            return Result.success(login,"登录成功!");
        }
        return Result.error("用户名或密码错误!");
    }

    /**
     * 发送邮箱验证码
     * @param userNum
     * @param emailAddress
     * @return
     */
    @GetMapping("/sendEmail")
    public Result sendEmail(@RequestParam("userNum") String userNum,
                            @RequestParam("emailAddress") String emailAddress){
        User user = userService.findByUserNum(userNum);
        if (null == user){
            return Result.error("请输入正确的管理员编号！");
        }
        if (!user.getUserEmail().equals(emailAddress)){
            return Result.error("请输入绑定的邮箱！");
        }
        String code = VerCode.getVerCode();
        SendEmailConfig.sendEmail(emailAddress,code);
        user.setCode(code);
        return Result.success(user,"发送成功,注意查收！");
    }

    /**
     * 修改密码判断与旧密码加密后是否相同
     * @param userId
     * @param password
     * @return
     */
    @GetMapping("/updatePassword")
    public Result updatePassword(@RequestParam("userId") int userId,
                                 @RequestParam("password") String password){
        User user = userService.findById(userId);
        if (user.getUserPassword().equals(SecureUtil.md5(password))){
            return Result.success("与旧密码相同");
        }
        return Result.error("密码不同");
    }
}

