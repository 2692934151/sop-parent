package com.gaoyang.sopweb.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @desc: 错误信息
 * @date: 2019/10/25 4:20 下午
 * @author: shuaizx
 * @version: 1.0
 */
@Data
@ToString
public class ErrorData {
    private String interfaceAttr;
    private String description;
    private String defaultData;
}
