package cn.lineon.reggie.controller;

import cn.lineon.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

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

    /**
     * 上传图片
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) throws IOException {
        log.info("文件上传！");
        //file是一个临时文件，需要转存到指定位置，本次请求完毕后就会删除
        //创建一个目录对象
        File dir = new File(basePath);
        //如果目录不存在创建一个目录
        if (!dir.exists()){
            dir.mkdirs();
        }
        //使用UUID处理文件名,防止文件名重复
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        file.transferTo(new File(basePath+fileName));
        return R.success(fileName);
    }

    /**
     * 文件下载
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        try {
            //创建输入流，读取文件
            FileInputStream fileInputStream = new FileInputStream(basePath+name);
            //获取输出流，往浏览器写文件
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] bytes=new byte[1024];
            int len=0;
            while ((len=fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            //关闭流
            fileInputStream.close();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
