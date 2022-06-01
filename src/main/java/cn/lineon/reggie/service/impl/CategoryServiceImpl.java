package cn.lineon.reggie.service.impl;

import cn.lineon.reggie.common.CustomException;
import cn.lineon.reggie.entity.Category;
import cn.lineon.reggie.entity.Dish;
import cn.lineon.reggie.entity.Setmeal;
import cn.lineon.reggie.mapper.CategoryMapper;
import cn.lineon.reggie.service.CategoryService;
import cn.lineon.reggie.service.DishService;
import cn.lineon.reggie.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 刘国庆
 * @version 1.0.0
 * @ClassName CategoryServiceImpl.java
 * @Description TODO
 * @createTime 2022年05月15日 20:29:00
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    /**
     * 根据ids删除分类，删除前进行关联判断
     * @param ids
     * @return
     */
    @Override
    public boolean remove(Long ids) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类ids进行查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,ids);
        int dishCount = dishService.count(dishLambdaQueryWrapper);
        //查询当前分类是否关联了菜品，如果关联则抛出一个异常
        if (dishCount>0){
            throw new CustomException("当前菜品已经关联菜品分类");
        }
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类ids进行查询
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,ids);
        int setmealCount = setmealService.count(setmealLambdaQueryWrapper);
        //查询当前分类是否关联了套餐，若果关联则抛出一个异常
        if (setmealCount>0){
            throw new CustomException("当前菜品已经关联套餐分类");
        }
        super.removeById(ids);
        return true;
    }
}
