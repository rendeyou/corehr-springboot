package com.bjsxt.corehr.handler.impl;

import com.bjsxt.corehr.constant.enums.OrgPageEnum;
import com.bjsxt.corehr.constant.enums.ResponseStatusEnum;
import com.bjsxt.corehr.constant.enums.StringEnum;
import com.bjsxt.corehr.handler.OrgPageHandler;
import com.bjsxt.corehr.pojo.po.org.Org;
import com.bjsxt.corehr.pojo.req.org.OrgUpdateReq;
import com.bjsxt.corehr.pojo.vo.ResponseVO;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @className: OrgBasePageHandler
 * @description: 组织信息处理器
 * @author: RenDeYou
 * @date: 2021/4/15 19:30
 */
@Component
@Slf4j
public class OrgBasePageHandler extends OrgPageHandler {

    @Override
    public OrgPageEnum getOrgPageEnum() {
        return OrgPageEnum.ORG_BASE_ENUM;
    }

    @Override
    public ResponseVO editSaveOrgInfo(OrgUpdateReq orgUpdateInfo) {
        ResponseVO responseVO = new ResponseVO();
        //转换
        Org org = new Org();
//        org = this.convertMapToBean(org, Org.class, orgUpdateInfo.getData());
        org = this.convertMapToBean(org, Org.class, orgUpdateInfo.getData(), true);
        log.info("组织信息：{}", org);
        //校验必填参数
        Map<String, Object> map = this.orgParamsCheck(org, responseVO);
        //校验不通过
        if (!(Boolean) map.get(StringEnum.FLAG.getStringName())) {
            return (ResponseVO) map.get(StringEnum.RESPONSE_VO.getStringName());
        }
        //校验通过...
        return responseVO;
    }

    /**
     * @param org
     * @param responseVO
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @description: 校验组织信息必填参数
     * @author: RenDeyou
     * @date: 2021/5/22 21:49
     */
    private Map<String, Object> orgParamsCheck(Org org, ResponseVO responseVO) {
        Map<String, Object> map = Maps.newHashMap();
        //校验通过标识：true通过 false未通过
        Boolean flag = true;

        Map<String, Object> idMap = this.StringNotNull(org.getId(), "id");
        if (!(Boolean) idMap.get(StringEnum.FLAG.getStringName())) {
            flag = false;
            responseVO.setResponseCode(ResponseStatusEnum.CHECK_ERROR.getResponseCode());
            responseVO.setResponseMsg((String) idMap.get(StringEnum.DESC.getStringName()));
            map.put(StringEnum.FLAG.getStringName(), flag);
            map.put(StringEnum.RESPONSE_VO.getStringName(), responseVO);
            return map;
        }

        Map<String, Object> nameMap = this.StringNotNull(org.getName(), "name");
        if (!(Boolean) nameMap.get(StringEnum.FLAG.getStringName())) {
            flag = false;
            responseVO.setResponseCode(ResponseStatusEnum.CHECK_ERROR.getResponseCode());
            responseVO.setResponseMsg((String) nameMap.get(StringEnum.DESC.getStringName()));
            map.put(StringEnum.FLAG.getStringName(), flag);
            map.put(StringEnum.RESPONSE_VO.getStringName(), responseVO);
            return map;
        }

        Map<String, Object> startDateMap = this.ObjectNotNull(org.getStartDate(), "startDate");
        if (!(Boolean) startDateMap.get(StringEnum.FLAG.getStringName())) {
            flag = false;
            responseVO.setResponseCode(ResponseStatusEnum.CHECK_ERROR.getResponseCode());
            responseVO.setResponseMsg((String) startDateMap.get(StringEnum.DESC.getStringName()));
            map.put(StringEnum.FLAG.getStringName(), flag);
            map.put(StringEnum.RESPONSE_VO.getStringName(), responseVO);
            return map;
        }

        map.put(StringEnum.FLAG.getStringName(), flag);
        return map;
    }

    @Override
    public ResponseVO addOrgOtherInfo(OrgUpdateReq orgUpdateInfo) {
        return null;
    }

    @Override
    public ResponseVO updateOrgOtherInfo(OrgUpdateReq orgUpdateInfo) {
        return null;
    }

    @Override
    public ResponseVO deleteOrgOtherInfo(OrgUpdateReq orgUpdateInfo) {
        return null;
    }
}
