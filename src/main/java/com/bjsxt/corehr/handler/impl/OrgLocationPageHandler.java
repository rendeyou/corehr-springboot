package com.bjsxt.corehr.handler.impl;

import com.bjsxt.corehr.constant.enums.OrgPageEnum;
import com.bjsxt.corehr.handler.OrgPageHandler;
import com.bjsxt.corehr.pojo.po.org.OrgLocation;
import com.bjsxt.corehr.pojo.req.org.OrgUpdateReq;
import com.bjsxt.corehr.pojo.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @className: OrgLocationPageHandler
 * @description: 组织驻地信息处理器
 * @author: RenDeYou
 * @date: 2021/4/15 19:35
 */
@Component
@Slf4j
public class OrgLocationPageHandler extends OrgPageHandler {

    @Override
    public OrgPageEnum getOrgPageEnum() {
        return OrgPageEnum.ORG_LOCATION_ENUM;
    }

    @Override
    public ResponseVO editSaveOrgInfo(OrgUpdateReq orgUpdateInfo) {
        return null;
    }

    @Override
    public ResponseVO addOrgOtherInfo(OrgUpdateReq orgUpdateInfo) {
        ResponseVO responseVO = new ResponseVO();
        //转换
        OrgLocation orgLocation = new OrgLocation();
        orgLocation = this.convertMapToBean(orgLocation, OrgLocation.class, orgUpdateInfo.getData());
        log.info("组织驻地信息:{}", orgLocation);
        //校验必填参数
        //校验不通过
        //校验通过...
        return responseVO;
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
