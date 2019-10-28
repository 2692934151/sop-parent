package com.gaoyang.sopweb.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @desc: 总接口信息描述
 * @date: 2019/10/22 1:27 下午
 * @author: shuaizx
 * @version: 1.0
 */
@Data
@ToString
public class InterfacesInfo {
    private String count;
    private String total;
    private List<InterfaceData> data;
}
