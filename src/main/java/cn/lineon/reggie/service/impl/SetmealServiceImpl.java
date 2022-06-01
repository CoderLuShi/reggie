package cn.lineon.reggie.service.impl;

import cn.lineon.reggie.dto.DishDto;
import cn.lineon.reggie.dto.SetmealDto;
import cn.lineon.reggie.entity.Dish;
import cn.lineon.reggie.entity.Setmeal;
import cn.lineon.reggie.entity.SetmealDish;
import cn.lineon.reggie.mapper.SetmealMapper;
import cn.lineon.reggie.service.SetmealDishService;
import cn.lineon.reggie.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 刘国庆
 * @version 1.0.0
 * @ClassName SetmealServiceImpl.java
 * @Description TODO
 * @createTime 2022年05月15日 21:28:00
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper,Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐基本信息
        this.save(setmealDto);
        //保存套餐菜品关联信息
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        //设置套餐ID
        setmealDishes=setmealDishes.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishes);
    }

    @Override
    @Transactional
    public void updateWithDish(SetmealDto setmealDto) {
        //更新套餐基本信息
        this.updateById(setmealDto);
        //删除所有菜品信息
        LambdaUpdateWrapper<SetmealDish> setmealDishLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        setmealDishLambdaUpdateWrapper.eq(SetmealDish::getSetmealId,setmealDto.getId());
        setmealDishService.remove(setmealDishLambdaUpdateWrapper);
        //添加菜品信息
        //保存套餐菜品关联信息
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        //设置套餐ID
        setmealDishes=setmealDishes.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        //将更改后的菜品关联信息插入
        setmealDishService.saveBatch(setmealDishes);
    }

    @Override
    @Transactional
    public void deleteWithDish(List<Long> ids) {
        //删除套餐信息
        this.removeByIds(ids);
        //删除套餐包含菜品信息
        LambdaUpdateWrapper<SetmealDish> setmealDishLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        setmealDishLambdaUpdateWrapper.in(SetmealDish::getSetmealId,ids);
        setmealDishService.remove(setmealDishLambdaUpdateWrapper);
    }

    @Override
    public SetmealDto getWithDish(Long id) {
        SetmealDto setmealDto = new SetmealDto();
        //查询套餐基本信息
        Setmeal setmeal = this.getById(id);
        BeanUtils.copyProperties(setmeal,setmealDto);
        //查询套餐包含菜品信息
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.eq(SetmealDish::getSetmealId,id);
        List<SetmealDish> list = setmealDishService.list(setmealDishLambdaQueryWrapper);
        setmealDto.setSetmealDishes(list);
        return setmealDto;
    }
}
