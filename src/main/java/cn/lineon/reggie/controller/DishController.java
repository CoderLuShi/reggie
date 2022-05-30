package cn.lineon.reggie.controller;

import cn.lineon.reggie.common.R;
import cn.lineon.reggie.dto.DishDto;
import cn.lineon.reggie.entity.Category;
import cn.lineon.reggie.entity.Dish;
import cn.lineon.reggie.service.CategoryService;
import cn.lineon.reggie.service.DishFlavorService;
import cn.lineon.reggie.service.DishService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 刘国庆
 * @version 1.0.0
 * @ClassName DishController.java
 * @Description TODO
 * @createTime 2022年05月16日 16:32:00
 */
@Slf4j
@RestController
@RequestMapping("/dish")
public class  DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private CategoryService categoryService;
    /**
     * 添加菜品
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        log.info(dishDto.toString());
        dishService.saveWithFlavor(dishDto);
        return R.success("添加菜品成功！");
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize,String name){
        //分页构造器
        Page<Dish> pageInfo = new Page<>(page, pageSize);
        Page<DishDto> dishDtoPage = new Page<>();
        //条件构造器
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(name!=null,Dish::getName,name);
        //添加排序条件
        dishLambdaQueryWrapper.orderByDesc(Dish::getUpdateTime);
        //执行分页查询
        pageInfo = dishService.page(pageInfo, dishLambdaQueryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo,dishDtoPage,"records");
        List<Dish> records = pageInfo.getRecords();
        List<DishDto> list=records.stream().map((item)->{
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item,dishDto);
            Long categoryId = item.getCategoryId();//分类ID
            //根据分类ID查询分类名称
            Category category = categoryService.getById(categoryId);
            String categoryName = category.getName();
            dishDto.setCategoryName(categoryName);
            return dishDto;
        }).collect(Collectors.toList());
        dishDtoPage.setRecords(list);
        return R.success(dishDtoPage);
    }

    /**
     * 删除菜品
     * @param param
     * @return
     */
    @DeleteMapping
    public R<String> delete(String param){
        return  null;
    }
}