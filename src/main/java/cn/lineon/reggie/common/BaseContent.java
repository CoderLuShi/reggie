package cn.lineon.reggie.common;

/**
 * @author 刘国庆
 * @version 1.0.0
 * @ClassName BaseContent.java
 * @Description 基于ThreadLocal封装的工具类，用于获取用户ID
 * @createTime 2022年05月15日 15:40:00
 */
public class BaseContent {
    private static ThreadLocal<Long> threadLocal=new ThreadLocal<>();
    public static long getCurrentId(){
        return threadLocal.get();
    }
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }
}
