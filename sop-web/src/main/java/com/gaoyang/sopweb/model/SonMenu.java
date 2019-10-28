package com.gaoyang.sopweb.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @desc: 基本文档data
 * @date: 2019/10/22 1:30 下午
 * @author: shuaizx
 * @version: 1.0
 */
@Data
@ToString
public class SonMenu {
    private String index;
    private String _id;
    private String name;
    private String project_id;
    private String desc;
    private String uid;
    private String add_time;
    private String up_time;
    private String __v;
    private List<InterfaceData> list;
}
