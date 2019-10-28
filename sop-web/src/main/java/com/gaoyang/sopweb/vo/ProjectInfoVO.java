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
public class ProjectInfoVO {
    private String projectId;
    private String projectName;
    private String token;
    private List<EnvVO> env;
}
