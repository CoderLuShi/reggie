package cn.lineon.reggie.service;

import cn.lineon.reggie.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrdersService extends IService<Orders> {
    void submit(Orders orders);
}
