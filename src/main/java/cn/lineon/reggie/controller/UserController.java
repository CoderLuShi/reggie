package cn.lineon.reggie.controller;

import cn.lineon.reggie.common.R;
import cn.lineon.reggie.entity.User;
import cn.lineon.reggie.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 刘国庆
 * @version 1.0.0
 * @ClassName UserController.java
 * @Description TODO
 * @createTime 2022年06月02日 18:36:00
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 获取验证码
     * @param user
     * @return
     */
    @PostMapping("/sendMessage")
    public R<String> sendMessage(User user){
        //获取手机号

        //生成验证码

        //发送验证码

        //保存验证码到Session

        return R.success("ok");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session){
        //获取手机号
        String phone = map.get("phone").toString();
        //获取验证码
        //模拟存入验证码
        map.put("code","1001");

        String code = map.get("code").toString();
        //比对验证码
        String codeInSession ="1001"; //session.getAttribute(phone).toString();
        //无法发送短信，此处为什么验证码都行
        if (true||(codeInSession!=null&&codeInSession.equals(code))){
            //查询数据库,手机号存在登录成功
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(userLambdaQueryWrapper);
            if (user==null){
                //手机号不存在自动注册
                user=new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            //登录成功
            session.setAttribute("user",user.getId());
            return R.success(user);
        }
        return R.error("登录失败！");
    }

}
