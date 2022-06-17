package cn.lineon.reggie.service.impl;

import cn.lineon.reggie.entity.ShoppingCart;
import cn.lineon.reggie.mapper.ShoppingCartMapper;
import cn.lineon.reggie.service.ShoppingCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
