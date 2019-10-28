package com.gaoyang.sopweb.core.integration;

import com.alibaba.fastjson.JSON;
import com.gaoyang.sopweb.exception.ParamConvertException;
import com.gaoyang.sopweb.model.*;
import com.gaoyang.sopweb.util.OkHttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @desc: 项目文档加载
 * @date: 2019/10/22 1:23 下午
 * @author: shuaizx
 * @version: 1.0
 */
@Service
public class LoadContextIntegration {

    @Value("${yapi.get_project}")
    private String getProjectUrl;

    @Value("${yapi.get_cat_menu}")
    private String getCatMenuUrl;

    @Value("${yapi.interface_list_cat}")
    private String getListCat;

    @Value("${yapi.interface_get}")
    private String getInterfaceDetail;

    @Value("${yapi.list_menu_interface}")
    private String getlistMenu;

    /**
     * 获取项目基本信息
     * @param token
     * @return 项目信息
     * @throws Exception
     */
    public ProjectData getProject(String token) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        String url = convertUrl(map, getProjectUrl);
        String bodyString = OkHttpClientUtil.sendGetRequest(url, new HashMap<>());
        ProjectData data = Optional.ofNullable(bodyString)
                .map(b -> JSON.parseObject(b, ProjectModel.class))
                .filter(p -> "0".equals(p.getErrcode()))
                .map(p -> p.getData())
                .orElseThrow(() -> new ParamConvertException("获取项目基本信息有误"));
        return data;
    }

    /**
     * 获取所有的菜单信息
     * @param project_id
     * @param token
     * @return
     * @throws Exception
     */
    public List<SonMenu> getCatMenu(String project_id, String token) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("project_id", "30");
        map.put("token", "7662108c9615f68724ba923e0b4b7e7c909d62341391bc43f7c0b7d4424fd9ff");
        String url = convertUrl(map, getCatMenuUrl);
        String bodyString = OkHttpClientUtil.sendGetRequest(url, new HashMap<>());
        List<SonMenu> list = Optional.ofNullable(bodyString)
                .map(b -> JSON.parseObject(b, CatMenuModel.class))
                .filter(c -> "0".equals(c.getErrcode()))
                .map(c -> c.getData())
                .orElseThrow(() -> new ParamConvertException("获取项目菜单列表转换有误"));
        return list;
    }

    public List<CatInterfaceModel> getListCat(String token, String catId, String page, String limit) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", "7662108c9615f68724ba923e0b4b7e7c909d62341391bc43f7c0b7d4424fd9ff");
        map.put("catid", "39");
        map.put("page", page);
        map.put("limit", limit);
        String url = convertUrl(map, getListCat);
        String bodyString = OkHttpClientUtil.sendGetRequest(url, new HashMap<>());
        return new ArrayList<>();
    }

    /**
     * 获取接口详细信息
     * @param token
     * @param id
     * @return
     * @throws Exception
     */
    public InterfaceAllData getIntefaceDetail(String token, String id) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("id", id);
        String url = convertUrl(map, getInterfaceDetail);
        String bodyString = OkHttpClientUtil.sendGetRequest(url, new HashMap<>());
        InterfaceAllData data = Optional.ofNullable(bodyString)
                .map(b -> JSON.parseObject(b, InterfaceDetail.class))
                .filter(i -> "0".equals(i.getErrcode()))
                .map(i -> i.getData())
                .orElseThrow(() -> new ParamConvertException("获取接口细节异常"));
        return data;
    }

    /**
     * 获取项目接口和菜单信息
     * @param project_id
     * @param token
     * @return
     * @throws Exception
     */
    public List<SonMenu> getInterfaceAndMenu(String project_id,String token) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        map.put("project_id",project_id);
        String url = convertUrl(map, getlistMenu);
        String bodyString = OkHttpClientUtil.sendGetRequest(url, new HashMap<>());
        List<SonMenu> menuList = Optional.ofNullable(bodyString)
                .map(b -> JSON.parseObject(b, InterfaceAndMenu.class))
                .filter(i -> "0".equals(i.getErrcode()))
                .map(i -> i.getData())
                .orElseThrow(() -> new ParamConvertException("获取接口菜单列表异常"));
        return menuList;
    }

    private String convertUrl(HashMap<String, String> map, String url) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url);
        if (stringBuilder.charAt(url.length() - 1) != '?') {
            stringBuilder.append('?');
        }
        for (String key : map.keySet()) {
            String s = map.get(key);
            if (s == null) {
                continue;
            }
            stringBuilder.append(key);
            stringBuilder.append("=");
            stringBuilder.append(s);
            stringBuilder.append('&');
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
