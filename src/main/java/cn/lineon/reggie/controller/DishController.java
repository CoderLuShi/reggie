package cn.lineon.reggie.controller;

import cn.lineon.reggie.common.R;
import cn.lineon.reggie.entity.Dish;
import cn.lineon.reggie.service.DishService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class DishController {
    @Autowired
    private DishService dishService;

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize){
        //分页构造器
        Page<Dish> pageInfo = new Page<>(page, pageSize);
        //执行查询
        pageInfo = dishService.page(pageInfo);
        return R.success(pageInfo);
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
