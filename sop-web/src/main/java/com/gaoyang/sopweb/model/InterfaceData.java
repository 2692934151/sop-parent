package com.gaoyang.sopweb.model;

import lombok.Data;
import lombok.ToString;

/**
 * @desc: 接口基本信息描述
 * @date: 2019/10/22 1:27 下午
 * @author: shuaizx
 * @version: 1.0
 */
@Data
@ToString
public class InterfaceData {
    private String edit_uid;
    private String status;
    private String index;
    private String tag;
    private String _id;
    private String method;
    private String catid;
    private String title;
    private String path;
    private String project_id;
    private String uid;
    private String add_time;
    private String up_time;
}
