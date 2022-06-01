package cn.lineon.reggie.dto;

import cn.lineon.reggie.entity.Setmeal;
import cn.lineon.reggie.entity.SetmealDish;

import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
