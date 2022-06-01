package cn.lineon.reggie.service.impl;

import cn.lineon.reggie.dto.DishDto;
import cn.lineon.reggie.entity.Dish;
import cn.lineon.reggie.entity.DishFlavor;
import cn.lineon.reggie.mapper.DishMapper;
import cn.lineon.reggie.service.DishFlavorService;
import cn.lineon.reggie.service.DishService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
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

    /**
     * 添加菜品信息
     * @param dishDto
     */
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

    /**
     * 更新菜品及其口味信息
     * @param dishDto
     */
    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        //保存菜品基本信息到菜品表
        this.updateById(dishDto);
        //获取菜品的ID
        Long id = dishDto.getId();
        //删除当前菜品口味信息
        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(dishFlavorLambdaQueryWrapper);
        //将修改后的菜品口味信息插入
        //给口味赋上菜品ID
        List<DishFlavor> flavors = dishDto.getFlavors();
        for (DishFlavor flavor : flavors) {
            flavor.setDishId(id);
        }
        //保存口味信息到口味表
        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 删除菜品及其口味信息
     * @param ids
     */
    @Override
    @Transactional
    public void deleteWithFlavor(List<Long> ids) {
        //删除菜品信息
        this.removeByIds(ids);
        //删除菜品口味信息
        LambdaUpdateWrapper<DishFlavor> dishFlavorLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        dishFlavorLambdaUpdateWrapper.in(DishFlavor::getDishId,ids);
        dishFlavorService.remove(dishFlavorLambdaUpdateWrapper);
    }

    /**
     * 根据ID查询菜品及其口味信息
     * @param id
     * @return
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        //根据ID查询菜品信息
        Dish dish = this.getById(id);
        DishDto dishDto = new DishDto();
        //对象拷贝
        BeanUtils.copyProperties(dish,dishDto);
        //根据ID查询口味信息
        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId,id);
        List<DishFlavor> list = dishFlavorService.list(dishFlavorLambdaQueryWrapper);
        dishDto.setFlavors(list);
        return dishDto;
    }
}
