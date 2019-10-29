package com.gaoyang.sopweb.core.service;

import com.alibaba.fastjson.JSON;
import com.gaoyang.sopweb.core.integration.LoadContextIntegration;
import com.gaoyang.sopweb.model.*;
import com.gaoyang.sopweb.vo.*;
import com.jiexun.transaction.common.log.Logger;
import com.jiexun.transaction.common.log.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @desc: 文档解析service
 * @date: 2019/10/23 6:54 下午
 * @author: shuaizx
 * @version: 1.0
 */
@Service
public class LoadContextService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadContextService.class);
    @Autowired
    private LoadContextIntegration loadContextIntegration;
    private static List<String> projectTokens = Arrays.asList("7662108c9615f68724ba923e0b4b7e7c909d62341391bc43f7c0b7d4424fd9ff");

    /**
     * 项目基本信息转化
     *
     * @return 项目信息
     * @throws Exception
     */
    public List<ProjectInfoVO> getProject() throws Exception {
        LOGGER.info("【项目信息开始加载-getProject-service】start");
        ArrayList<ProjectInfoVO> list = new ArrayList<>();
        LOGGER.info("【项目信息加载-getProject-service】加载项目tokens为={}", projectTokens);
        for (String token : projectTokens) {
            ProjectData project = loadContextIntegration.getProject(token);
            ProjectInfoVO projectInfoVO = getProjectInfoVO(project, token);
            list.add(projectInfoVO);
        }
        LOGGER.info("【项目信息加载-getProject-service】查询到的项目集合信息为={}", list);
        return list;
    }

    /**
     * 获取项目接口和菜单信息
     *
     * @param project_id
     * @param token
     * @return
     * @throws Exception
     */
    public List<SonMenuVO> getInterfaceAndMenu(String project_id, String token) throws Exception {
        List<SonMenu> interfaceAndMenu = loadContextIntegration.getInterfaceAndMenu(project_id, token);
        List<SonMenuVO> list=getListSonVO(interfaceAndMenu);
        LOGGER.info("【获取接口菜单信息-getInterfaceAndMenu-service】查询到的接口菜单信息为={}", list);
        return list;
    }

    /**
     * 获取接口详细信息
     *
     * @param token
     * @param id
     * @return
     * @throws Exception
     */
    public InterfaceAllVO getIntefaceDetail(String token, String id) throws Exception {
        InterfaceAllData intefaceDetail = loadContextIntegration.getIntefaceDetail(token, id);
        InterfaceAllVO interfaceAllVO = getInterfaceAllVO(intefaceDetail);
        LOGGER.info("【获取接口菜单信息-getIntefaceDetail-service】查询到的接口详细信息为={}", interfaceAllVO);
        return interfaceAllVO;
    }


    private ProjectInfoVO getProjectInfoVO(ProjectData data, String token) {
        ProjectInfoVO infoVO = new ProjectInfoVO();
        infoVO.setProjectId(data.get_id());
        infoVO.setProjectName(data.getName());
        infoVO.setToken(token);
        List<EnvModel> envs = data.getEnv();
        ArrayList<EnvVO> list = new ArrayList<>();
        for (EnvModel envModel : envs) {
            EnvVO envVO = new EnvVO();
            envVO.setName(envModel.getName());
            envVO.setDomain(envModel.getDomain());
            list.add(envVO);
        }
        infoVO.setEnv(list);
        return infoVO;
    }

    private InterfaceAllVO getInterfaceAllVO(InterfaceAllData intefaceDetail) {
        InterfaceAllVO interfaceAllVO = new InterfaceAllVO();
        long longDate = Long.parseLong(intefaceDetail.getUp_time());
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(longDate*1000);
        interfaceAllVO.setUpTime(format);
        interfaceAllVO.setDesc(intefaceDetail.getDesc());
        interfaceAllVO.setMethod(intefaceDetail.getMethod());
        interfaceAllVO.setPath(intefaceDetail.getPath());
        //请求解析
        String reqBodyOther = intefaceDetail.getReq_body_other();
        ReqBodyTmp reqBodyTmp = JSON.parseObject(reqBodyOther, ReqBodyTmp.class);
        ReqBodyVO reqBodyVO = getReqBodyVO(reqBodyTmp);
        interfaceAllVO.setReqBodyOther(reqBodyVO);

        interfaceAllVO.setReqBodyType(intefaceDetail.getReq_body_type());
        interfaceAllVO.setResBodyType(intefaceDetail.getRes_body_type());
        List<ReqHeader> req_headers = intefaceDetail.getReq_headers();
        List<ReqHeaderVO> reqHeader=new ArrayList<>();
        for (ReqHeader header:req_headers){
            ReqHeaderVO reqHeaderVO = new ReqHeaderVO();
            BeanUtils.copyProperties(header,reqHeaderVO);
            reqHeader.add(reqHeaderVO);
        }
        interfaceAllVO.setReqHeader(reqHeader);
        //响应解析
        String resBody = intefaceDetail.getRes_body();
        ResBodyTmp resBodyTmp = JSON.parseObject(resBody, ResBodyTmp.class);
        ResBodyVO resBodyVO = getResBodyVO(resBodyTmp);
        interfaceAllVO.setResBody(resBodyVO);
        interfaceAllVO.setStatus(intefaceDetail.getStatus());
        interfaceAllVO.setTitle(intefaceDetail.getTitle());
        interfaceAllVO.setUsername(intefaceDetail.getUsername());
        List<String> tags = intefaceDetail.getTag();
        interfaceAllVO.setTag("1.0");
        if(tags.size()>0){
            interfaceAllVO.setTag(tags.get(tags.size()-1));
        }
        return interfaceAllVO;
    }

    private ResBodyVO getResBodyVO(ResBodyTmp resBodyTmp) {
        ResBodyVO resBodyVO = new ResBodyVO();
        if(resBodyTmp==null){
            return resBodyVO;
        }
        resBodyVO.setSchema(resBodyTmp.$schema);
        resBodyVO.setType(resBodyTmp.getType());

        List<ReDetail> ReDetails = new ArrayList<>();
        List<ErrorData> errorPublic=new ArrayList<>();
        List<ErrorData> errorBusiness=new ArrayList<>();
        List<String> required = resBodyTmp.getRequired();

        Map<String, ReDetail> properties = resBodyTmp.getProperties();
        for (String key : properties.keySet()) {
            ReDetail detail = properties.get(key);
            if(key.startsWith("$ERRORPUBLIC_")){
                ErrorData errorData = new ErrorData();
                errorData.setDescription(detail.getDescription());
                errorData.setDefaultData(detail.getDefaultData());
                String substring = key.substring(13);
                errorData.setInterfaceAttr(substring);
                errorPublic.add(errorData);
            }else if(key.startsWith("$ERRORBUSINESS_")){
                ErrorData errorData = new ErrorData();
                errorData.setDescription(detail.getDescription());
                errorData.setDefaultData(detail.getDefaultData());
                String substring = key.substring(15);
                errorData.setInterfaceAttr(substring);
                errorBusiness.add(errorData);
            }else {
                detail.setIsMust("非必须");
                if (required != null && required.contains(key)) {
                    detail.setIsMust("必须");
                }
                detail.setInterfaceAttr(key);
                ReDetails.add(detail);
            }
        }
        resBodyVO.setReDetails(ReDetails);
        resBodyVO.setErrorBusiness(errorBusiness);
        resBodyVO.setErrorPublic(errorPublic);
        return resBodyVO;
    }

    private ReqBodyVO getReqBodyVO(ReqBodyTmp reqBodyTmp) {
        ReqBodyVO reqBodyVO = new ReqBodyVO();
        if(reqBodyTmp==null){
            return reqBodyVO;
        }
        reqBodyVO.setSchema(reqBodyTmp.$schema);
        reqBodyVO.setType(reqBodyTmp.getType());
        List<ReDetail> props = new ArrayList<>();
        List<String> required = reqBodyTmp.getRequired();
        Map<String, ReDetail> properties = reqBodyTmp.getProperties();
        for (String key : properties.keySet()) {
            ReDetail detail = properties.get(key);
            if(key.startsWith("$INTERFACEDESC")){
                reqBodyVO.setInterfaceDesc(detail.getDescription());
            }
            else {
                detail.setIsMust("非必须");
                if (required != null && required.contains(key)) {
                    detail.setIsMust("必须");
                }
                detail.setInterfaceAttr(key);
                props.add(detail);
            }
        }
        reqBodyVO.setProperties(props);
        return reqBodyVO;
    }
    private List<SonMenuVO> getListSonVO(List<SonMenu> interfaceAndMenu) {
        List<SonMenuVO> list=new ArrayList<>();
        for (SonMenu sonMenu:interfaceAndMenu){
            SonMenuVO sonMenuVO = new SonMenuVO();
            sonMenuVO.setId(sonMenu.get_id());
            sonMenuVO.setName(sonMenu.getName());
            ArrayList<InterfaceVO> arrayList = new ArrayList<>();
            List<InterfaceData> sonMenuList = sonMenu.getList();
            for (InterfaceData data:sonMenuList){
                InterfaceVO interfaceVO = new InterfaceVO();
                interfaceVO.setId(data.get_id());
                interfaceVO.setTitle(data.getTitle());
                arrayList.add(interfaceVO);
            }
            sonMenuVO.setList(arrayList);
            list.add(sonMenuVO);
        }
        return list;
    }
}
