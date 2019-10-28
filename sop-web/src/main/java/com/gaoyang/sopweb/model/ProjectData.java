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
public class ProjectData {
    private String switch_notice;
    private String is_mock_open;
    private String strice;
    private String is_json5;
    private String _id;
    private String name;
    private String basepath;
    private String project_type;
    private String uid;
    private String group_id;
    private String icon;
    private String color;
    private String add_time;
    private String up_time;
    private List<EnvModel> env;
    private String tag;
    private String cat;
    private String role;
}
