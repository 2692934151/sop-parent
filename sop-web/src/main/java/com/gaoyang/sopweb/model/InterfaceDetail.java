package com.gaoyang.sopweb.model;

import lombok.Data;
import lombok.ToString;

/**
 * @desc: 接口详细信息类
 * @date: 2019/10/22 1:27 下午
 * @author: shuaizx
 * @version: 1.0
 */
@Data
@ToString
public class InterfaceDetail {
    private String errcode;
    private String errmsg;
    private InterfaceAllData data;
}
