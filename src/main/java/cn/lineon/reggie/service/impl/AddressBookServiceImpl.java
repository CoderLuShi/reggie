package cn.lineon.reggie.service.impl;

import cn.lineon.reggie.entity.AddressBook;
import cn.lineon.reggie.mapper.AddressBookMapper;
import cn.lineon.reggie.service.AddressBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.tomcat.jni.Address;
import org.springframework.stereotype.Service;

/**
 * @author 刘国庆
 * @version 1.0.0
 * @ClassName AddressBookServiceImpl.java
 * @Description TODO
 * @createTime 2022年06月02日 18:03:00
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
