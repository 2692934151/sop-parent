package com.gaoyang.sopweb.model;

import lombok.Data;
import lombok.ToString;

/**
 * @desc: 项目环境描述
 * @date: 2019/10/22 1:27 下午
 * @author: shuaizx
 * @version: 1.0
 */
@Data
@ToString
public class EnvModel {
    private String header;
    private String global;
    private String _id;
    private String name;
    private String domain;

}
