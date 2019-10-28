package com.gaoyang.sopweb.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @desc: 接口菜单列表
 * @date: 2019/10/22 4:22 下午
 * @author: shuaizx
 * @version: 1.0
 */
@Data
@ToString
public class InterfaceAndMenu {
    private String errcode;
    private String errmsg;
    private List<SonMenu> data;
}
