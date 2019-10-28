package com.gaoyang.sopweb.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @desc: 项目信息展示类
 * @date: 2019/10/23 7:14 下午
 * @author: shuaizx
 * @version: 1.0
 */
@Data
@ToString
public class InterfaceAllVO {
    private String status;
    private String method;
    private String title;
    private String path;
    private String upTime;
    private List<ReqHeaderVO> reqHeader;
    private ReqBodyVO reqBodyOther;
    private ResBodyVO resBody;
    private String desc;
    private String reqBodyType;
    private String resBodyType;
    private String username;
    private String tag;
}
