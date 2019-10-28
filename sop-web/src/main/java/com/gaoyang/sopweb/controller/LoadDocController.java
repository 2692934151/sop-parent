package com.gaoyang.sopweb.controller;

import com.gaoyang.sopweb.core.service.LoadContextService;
import com.gaoyang.sopweb.model.SonMenu;
import com.gaoyang.sopweb.vo.InterfaceAllVO;
import com.gaoyang.sopweb.vo.ProjectInfoVO;
import com.gaoyang.sopweb.vo.SonMenuVO;
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
    @Autowired
    private LoadContextService loadContext;

    @GetMapping("/project/get")
    public List<ProjectInfoVO> getProjects() {

        try {
            List<ProjectInfoVO> projects = loadContext.getProject();
            return projects;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @GetMapping("/getInterfaceDetail")
    public InterfaceAllVO getInterfaceDetail(@RequestParam String token, @RequestParam String id){
        try {
            InterfaceAllVO intefaceDetail = loadContext.getIntefaceDetail(token, id);
            return intefaceDetail;
        } catch (Exception e) {
            e.printStackTrace();
            return new InterfaceAllVO();
        }
    }
    @GetMapping("/InterfaceAndMenu")
    public List<SonMenuVO> InterfaceAndMenu(@RequestParam String projectId,@RequestParam String token){
        try {
            List<SonMenuVO> interfaceAndMenu = loadContext.getInterfaceAndMenu(projectId, token);
            return interfaceAndMenu;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
