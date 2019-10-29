package com.gaoyang.sopweb.controller;

import com.gaoyang.sopweb.core.service.LoadContextService;
import com.gaoyang.sopweb.model.SonMenu;
import com.gaoyang.sopweb.util.LogExceptionWapper;
import com.gaoyang.sopweb.vo.InterfaceAllVO;
import com.gaoyang.sopweb.vo.ProjectInfoVO;
import com.gaoyang.sopweb.vo.SonMenuVO;
import com.jiexun.transaction.common.log.Logger;
import com.jiexun.transaction.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanghc
 */
@RestController
public class LoadDocController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadDocController.class);

    @Autowired
    private LoadContextService loadContext;

    @GetMapping("/project/get")
    public List<ProjectInfoVO> getProjects() {
        try {
            LOGGER.info("【全部项目开始加载-getProjects-Controller】start");
            List<ProjectInfoVO> projects = loadContext.getProject();
            LOGGER.info("【返回到前端的项目信息-getProjects-Controller】结果={}",projects);
            return projects;
        } catch (Exception e) {
            LOGGER.error("【返回到前端的项目信息-getProjects-Controller】发生异常e={}", LogExceptionWapper.getStackTrace(e));
            return new ArrayList<>();
        }
    }

    @GetMapping("/getInterfaceDetail")
    public InterfaceAllVO getInterfaceDetail(@RequestParam String token, @RequestParam String id){
        try {
            LOGGER.info("【获取接口详细信息-getInterfaceDetail-Controller】入参,token={},id={}",token,id);
            if(token==null||id==null){
                LOGGER.warn("【获取接口详细信息-getInterfaceDetail-Controller】参数校验失败");
                return new InterfaceAllVO();
            }
            InterfaceAllVO intefaceDetail = loadContext.getIntefaceDetail(token, id);
            LOGGER.info("【获取接口详细信息-getInterfaceDetail-Controller】获取到的接口信息为={}",intefaceDetail);
            return intefaceDetail;
        } catch (Exception e) {
            LOGGER.error("【获取接口详细信息-getInterfaceDetail-Controller】发生异常e={}", LogExceptionWapper.getStackTrace(e));
            return new InterfaceAllVO();
        }
    }
    @GetMapping("/InterfaceAndMenu")
    public List<SonMenuVO> InterfaceAndMenu(@RequestParam String projectId,@RequestParam String token){
        try {
            LOGGER.info("【获取接口菜单信息-InterfaceAndMenu-Controller】入参,token={},projectId={}",token,projectId);
            List<SonMenuVO> interfaceAndMenu = loadContext.getInterfaceAndMenu(projectId, token);
            LOGGER.info("【获取接口菜单信息-InterfaceAndMenu-Controller】结果={}",interfaceAndMenu);
            return interfaceAndMenu;
        } catch (Exception e) {
            LOGGER.error("【获取接口菜单信息-InterfaceAndMenu-Controller】发生异常e={}", LogExceptionWapper.getStackTrace(e));
            return new ArrayList<>();
        }
    }

}
