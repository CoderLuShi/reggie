package cn.lineon.reggie.service;

import cn.lineon.reggie.dto.DishDto;
import cn.lineon.reggie.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 刘国庆
 * @version 1.0.0
 * @ClassName DishServiceService.java
 * @Description TODO
 * @createTime 2022年05月15日 21:25:00
 */
public interface DishService extends IService<Dish> {
    /**
     * 添加菜品及其口味信息
     * @param dishDto
     */
    void saveWithFlavor(DishDto dishDto);

    /**
     * 更新菜品及其口味信息
     * @param dishDto
     */
    void updateWithFlavor(DishDto dishDto);

    /**
     * 删除菜品及其口味信息
     * @param ids
     */
    void deleteWithFlavor(List<Long> ids);

    /**
     * 根据ID获取菜品信息及其口味信息
     * @param id
     * @return
     */
    DishDto getByIdWithFlavor(Long id);
}
