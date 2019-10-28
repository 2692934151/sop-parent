package com.gaoyang.sopweb.model;

import lombok.Data;
import lombok.ToString;

/**
 * @desc: 分类下的接口列表
 * @date: 2019/10/22 1:27 下午
 * @author: shuaizx
 * @version: 1.0
 */
@Data
@ToString
public class CatInterfaceModel {
    private String errcode;
    private String errmsg;
    private InterfacesInfo data;
}
