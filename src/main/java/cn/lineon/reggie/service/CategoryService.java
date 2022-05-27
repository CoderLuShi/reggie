package cn.lineon.reggie.service;

import cn.lineon.reggie.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * @author 刘国庆
 * @version 1.0.0
 * @ClassName CategoryService.java
 * @Description TODO
 * @createTime 2022年05月15日 20:28:00
 */

public interface CategoryService extends IService<Category> {
    /**
     * 删除分类信息
     * @param ids
     * @return
     */
    boolean remove(Long ids);
}
