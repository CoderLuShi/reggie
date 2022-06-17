package cn.lineon.reggie.service.impl;

import cn.lineon.reggie.entity.OrderDetail;
import cn.lineon.reggie.mapper.OrderDetailMapper;
import cn.lineon.reggie.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}