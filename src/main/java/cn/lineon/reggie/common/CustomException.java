package cn.lineon.reggie.common;

/**
 * @author 刘国庆
 * @version 1.0.0
 * @ClassName CustomException.java
 * @Description TODO
 * @createTime 2022年05月15日 21:49:00
 */
public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
