package cn.lineon.reggie.service.impl;


import cn.lineon.reggie.entity.Employee;
import cn.lineon.reggie.mapper.EmployeeMapper;
import cn.lineon.reggie.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService {

}
