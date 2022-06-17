package cn.lineon.reggie.controller;

import cn.lineon.reggie.common.BaseContext;
import cn.lineon.reggie.common.R;
import cn.lineon.reggie.entity.ShoppingCart;
import cn.lineon.reggie.service.ShoppingCartService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    /**
     * 菜品添加购物车
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){
        log.info(shoppingCart.toString());
        //获取当前用户ID
        long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);
        //判断添加的是菜品还是套餐
        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (shoppingCart.getDishId()!=null){
            //判断当前菜品是否存在并且口味一致，符合+1，不符合添加一条
            shoppingCartLambdaQueryWrapper
                    .eq(ShoppingCart::getUserId,shoppingCart.getUserId())
                    .eq(ShoppingCart::getDishId,shoppingCart.getDishId())
                    .eq(shoppingCart.getDishFlavor()!=null,ShoppingCart::getDishFlavor,shoppingCart.getDishFlavor());
            ShoppingCart dish = shoppingCartService.getOne(shoppingCartLambdaQueryWrapper);
            if (dish!=null){
                //存在且口味相同+1
                dish.setNumber(dish.getNumber()+1);
                shoppingCartService.updateById(dish);
                shoppingCart=dish;
            }else {
                //不存在添加一条
                shoppingCartService.save(shoppingCart);
            }
        }else{
            //判断当前套餐是否存在,存在则数量+1，不存在则添加
            shoppingCartLambdaQueryWrapper
                    .eq(ShoppingCart::getUserId,shoppingCart.getUserId())
                    .eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
            ShoppingCart setmeal = shoppingCartService.getOne(shoppingCartLambdaQueryWrapper);
            if (setmeal!=null){
                //存在数量+1
                setmeal.setNumber(setmeal.getNumber()+1);
                shoppingCartService.updateById(setmeal);
                shoppingCart=setmeal;
            }else {
                //不存在添加一条
                shoppingCartService.save(shoppingCart);
            }
        }
        return R.success(shoppingCart);
    }

    /**
     * 查询购物车数据
     * @return
     */
    @GetMapping("/list")
    public R<List<ShoppingCart>> list(){
        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        shoppingCartLambdaQueryWrapper
                .eq(ShoppingCart::getUserId,BaseContext.getCurrentId());
        List<ShoppingCart> list = shoppingCartService.list(shoppingCartLambdaQueryWrapper);
        return R.success(list);
    }

    /**
     * 渐少菜品份数
     * @param shoppingCart
     * @return
     */
    @PostMapping("/sub")
    public R<String> sub(@RequestBody ShoppingCart shoppingCart){
        log.info(shoppingCart.toString());
        LambdaUpdateWrapper<ShoppingCart> shoppingCartLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (shoppingCart.getDishId()!=null){
            shoppingCartLambdaQueryWrapper
                    .eq(ShoppingCart::getDishId,shoppingCart.getDishId());
            shoppingCart=shoppingCartService.getOne(shoppingCartLambdaQueryWrapper);
            shoppingCartLambdaUpdateWrapper
                    .set(ShoppingCart::getNumber,shoppingCart.getNumber()-1)
                    .eq(ShoppingCart::getDishId,shoppingCart.getDishId());
            shoppingCartService.update(shoppingCartLambdaUpdateWrapper);
        }else {
            shoppingCartLambdaQueryWrapper
                    .eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
            shoppingCart=shoppingCartService.getOne(shoppingCartLambdaQueryWrapper);
            shoppingCartLambdaUpdateWrapper
                    .set(ShoppingCart::getNumber,shoppingCart.getNumber())
                    .eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
            shoppingCartService.update(shoppingCartLambdaUpdateWrapper);
        }
        return R.success("减去成功！");
    }

    /**
     * 清空购物车
     */
    @DeleteMapping("/clean")
    public R<String> clean(){
        LambdaUpdateWrapper<ShoppingCart> shoppingCartLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        shoppingCartLambdaUpdateWrapper
                .eq(ShoppingCart::getUserId,BaseContext.getCurrentId());
        shoppingCartService.remove(shoppingCartLambdaUpdateWrapper);
        return R.success("清除购物车成功！");
    }
}
