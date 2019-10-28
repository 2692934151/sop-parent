package com.gaoyang.sopweb.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @desc: 菜单列表类
 * @date: 2019/10/22 1:27 下午
 * @author: shuaizx
 * @version: 1.0
 */
@Data
@ToString
public class CatMenuModel {
    private String errcode;
    private String errmsg;
    private List<SonMenu> data;
}
