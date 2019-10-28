package com.gaoyang.sopweb.exception;

/**
 * @desc: 转换异常
 * @date: 2019/10/22 5:03 下午
 * @author: shuaizx
 * @version: 1.0
 */
public class ParamConvertException extends RuntimeException{
    public ParamConvertException() {
    }

    public ParamConvertException(String message) {
        super(message);
    }
}
