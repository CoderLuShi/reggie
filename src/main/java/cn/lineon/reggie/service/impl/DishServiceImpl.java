package cn.lineon.reggie.service.impl;

import cn.lineon.reggie.entity.Dish;
import cn.lineon.reggie.mapper.DishMapper;
import cn.lineon.reggie.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 刘国庆
 * @version 1.0.0
 * @ClassName DishServiceImpl.java
 * @Description TODO
 * @createTime 2022年05月15日 21:25:00
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
