package com.gaoyang.sopweb.model;

import lombok.Data;
import lombok.ToString;

/**
 * @desc: 请求头信息
 * @date: 2019/10/22 1:27 下午
 * @author: shuaizx
 * @version: 1.0
 */
@Data
@ToString
public class ReqHeader {
    private String required;
    private String name;
    private String value;
    private String example;
    private String desc;
}
