package cn.lineon.reggie.service;

import cn.lineon.reggie.dto.DishDto;
import cn.lineon.reggie.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 刘国庆
 * @version 1.0.0
 * @ClassName DishServiceService.java
 * @Description TODO
 * @createTime 2022年05月15日 21:25:00
 */
public interface DishService extends IService<Dish> {
    void saveWithFlavor(DishDto dishDto);
}
