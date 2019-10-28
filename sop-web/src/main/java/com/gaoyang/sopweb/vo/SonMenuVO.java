package com.gaoyang.sopweb.vo;

import com.gaoyang.sopweb.model.InterfaceData;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @desc: 接口菜单vo
 * @date: 2019/10/24 1:34 下午
 * @author: shuaizx
 * @version: 1.0
 */
@Data
@ToString
public class SonMenuVO {
    private String name;
    private String id;
    private List<InterfaceVO> list;
}
