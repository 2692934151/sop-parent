package com.gaoyang.sopweb.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @desc: 接口请求vo
 * @date: 2019/10/24 9:43 上午
 * @author: shuaizx
 * @version: 1.0
 */
@Data
@ToString
public class ReqBodyVO {
    private String schema;
    private String type;
    private List<ReDetail> properties;
    private String description;
    private String interfaceDesc;
}
