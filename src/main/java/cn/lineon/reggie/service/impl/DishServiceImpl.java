package cn.lineon.reggie.service.impl;

import cn.lineon.reggie.dto.DishDto;
import cn.lineon.reggie.entity.Dish;
import cn.lineon.reggie.entity.DishFlavor;
import cn.lineon.reggie.mapper.DishMapper;
import cn.lineon.reggie.service.DishFlavorService;
import cn.lineon.reggie.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 刘国庆
 * @version 1.0.0
 * @ClassName DishServiceImpl.java
 * @Description TODO
 * @createTime 2022年05月15日 21:25:00
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    public DishFlavorService dishFlavorService;
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品基本信息到菜品表
        this.save(dishDto);
        //获取菜品的ID
        Long id = dishDto.getId();
        //给口味赋上菜品ID
        List<DishFlavor> flavors = dishDto.getFlavors();
        for (DishFlavor flavor : flavors) {
            flavor.setDishId(id);
        }
        //保存口味信息到口味表
        dishFlavorService.saveBatch(flavors);
    }
}
