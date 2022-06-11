package cn.lineon.reggie.service.impl;

import cn.lineon.reggie.entity.User;
import cn.lineon.reggie.mapper.UserMapper;
import cn.lineon.reggie.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 刘国庆
 * @version 1.0.0
 * @ClassName UserServiceImpl.java
 * @Description TODO
 * @createTime 2022年06月02日 18:32:00
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
