package com.gaoyang.sopweb.model;

import lombok.Data;
import lombok.ToString;

/**
 * @desc: 项目信息类
 * @date: 2019/10/22 1:27 下午
 * @author: shuaizx
 * @version: 1.0
 */
@Data
@ToString
public class ProjectModel {
    private String errcode;
    private String errmsg;
    private ProjectData data;
}
