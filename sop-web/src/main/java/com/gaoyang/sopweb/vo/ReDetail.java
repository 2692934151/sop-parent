package com.gaoyang.sopweb.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @desc: 字段细节信息
 * @date: 2019/10/24 9:55 上午
 * @author: shuaizx
 * @version: 1.0
 */
@Data
@ToString
public class ReDetail {
    private String interfaceAttr;
    private String type;
    private String description;
    private String defaultData;
    private String pattern;
    private String minLength;
    private String maxLength;
    private String isMust;
}
