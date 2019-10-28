package com.gaoyang.sopweb.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @desc: 接口信息整体细节描述
 * @date: 2019/10/22 1:27 下午
 * @author: shuaizx
 * @version: 1.0
 */
@Data
@ToString
public class InterfaceAllData {
    private String query_path;
    private String edit_uid;
    private String status;
    private String api_opened;
    private List<String> tag;
    private String _id;
    private String method;
    private String catid;
    private String title;
    private String path;
    private String project_id;
    private String uid;
    private String add_time;
    private String type;
    private String req_body_is_json_schema;
    private String res_body_is_json_schema;
    private String index;
    private String req_params;
    private String res_body_type;
    private String up_time;
    private String req_query;
    private List<ReqHeader> req_headers;
    private String req_body_form;
    private String __v;
    private String req_body_other;
    private String markdown;
    private String desc;
    private String res_body;
    private String req_body_type;
    private String username;
}
