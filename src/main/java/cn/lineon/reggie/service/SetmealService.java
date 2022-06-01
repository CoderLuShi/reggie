package cn.lineon.reggie.service;

import cn.lineon.reggie.dto.DishDto;
import cn.lineon.reggie.dto.SetmealDto;
import cn.lineon.reggie.entity.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 刘国庆
 * @version 1.0.0
 * @ClassName Setmeal.java
 * @Description TODO
 * @createTime 2022年05月15日 21:27:00
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     * 保存套餐及其包含菜品信息
     * @param setmealDto
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     * 更新套餐及其包含菜品信息
     * @param setmealDto
     */
    void updateWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐及其包含菜品信息
     * @param ids
     */
    void deleteWithDish(List<Long> ids);

    /**
     * 根据ID查询套餐及其包含菜品信息
     * @param id
     * @return
     */
    SetmealDto getWithDish(Long id);
}
