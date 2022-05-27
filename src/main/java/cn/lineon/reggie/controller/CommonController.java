package cn.lineon.reggie.controller;

import cn.lineon.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author 刘国庆
 * @version 1.0.0
 * @ClassName CommonController.java
 * @Description TODO
 * @createTime 2022年05月25日 16:43:00
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Value("${reggie.path}")
    private String basePath;
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) throws IOException {
        log.info("文件上传！");
        file.transferTo(new File(basePath+file.getOriginalFilename()));
        return null;
    }
}
