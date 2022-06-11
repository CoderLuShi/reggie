package cn.lineon.reggie.mapper;

import cn.lineon.reggie.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 刘国庆
 * @version 1.0.0
 * @ClassName UserMapper.java
 * @Description TODO
 * @createTime 2022年06月02日 18:31:00
 *
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
