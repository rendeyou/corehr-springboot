package com.bjsxt.corehr.controller;

import com.bjsxt.corehr.constant.EsConstant;
import com.bjsxt.corehr.constant.RedisConstant;
import com.bjsxt.corehr.constant.enums.OrgPageEnum;
import com.bjsxt.corehr.constant.enums.ResponseStatusEnum;
import com.bjsxt.corehr.constant.enums.StringEnum;
import com.bjsxt.corehr.handler.OrgPageHandler;
import com.bjsxt.corehr.handler.OrgPageHandlerFactory;
import com.bjsxt.corehr.pojo.po.org.Org;
import com.bjsxt.corehr.pojo.req.org.OrgUpdateReq;
import com.bjsxt.corehr.pojo.vo.ResponseVO;
import com.bjsxt.corehr.utils.ElasticSearchQueryParams;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @className: OrgController
 * @description: 组织控制器
 * @author: RenDeYou
 * @date: 2021/4/18 22:14
 */
@Slf4j
@Api(value = "OrgController", description = "组织控制器")
@RestController
@RequestMapping("/orgController")
public class OrgController {

    /**
     * @param str
     * @return com.bjsxt.corehr.pojo.vo.ResponseVO
     * @description: //测试自定义传参类
     * @author: RenDeyou
     * @date: 2022/3/5 12:43
     */
    @ApiOperation(value = "elasticSearchQueryParamsTest", notes = "测试自定义传参类")
    @PostMapping("/elasticSearchQueryParamsTest")
    public ResponseVO elasticSearchQueryParamsTest(String str) {
        ResponseVO responseVO = new ResponseVO();
        //赋值
        ElasticSearchQueryParams<Org> elasticSearchQueryParams = ElasticSearchQueryParams.<Org>builder()
                .indexNames(EsConstant.Org.ORG_INDEX)
                .typeNames(EsConstant.Org.ORG_TYPE)
                .clazz(Org.class)
                .build();
        //取值
        log.info("elasticSearchQueryParams.getIndexNames()={}", elasticSearchQueryParams.getIndexNames());
        log.info("elasticSearchQueryParams.getTypeNames()={}", elasticSearchQueryParams.getTypeNames());
        log.info("elasticSearchQueryParams.getClazz()={}", elasticSearchQueryParams.getClazz());
        log.info("elasticSearchQueryParams.getIncludeFields()={}", elasticSearchQueryParams.getIncludeFields());
        log.info("elasticSearchQueryParams.getExcludeFields()={}", elasticSearchQueryParams.getExcludeFields());
        return responseVO;
    }

    /**
     * @param str
     * @return com.bjsxt.corehr.pojo.vo.ResponseVO
     * @description: //测试字符串常量RedisKey拼接
     * @author: RenDeyou
     * @date: 2022/3/4 17:25
     */
    @ApiOperation(value = "getConstantKey", notes = "获取字符串常量RedisKey")
    @PostMapping("/getConstantKey")
    public ResponseVO getConstantKey(String str) {
        ResponseVO responseVO = new ResponseVO();
        String communicationKey = RedisConstant.getRedisKey(RedisConstant.Org.ORG_CONDITION_MAP_KEY, "communication");
        log.info("communicationKey={}", communicationKey);
        String locationKey = RedisConstant.getRedisKey(RedisConstant.Org.ORG_CONDITION_MAP_KEY, "location");
        System.out.println("locationKey = " + locationKey);
        log.info("locationKey={}", locationKey);
        return responseVO;
    }

    /**
     * {
     * "isDelete" : 0,
     * "orgPageId":"org_base_pageId",
     * "data": {"id":"12345","parentId":"00000","name":"地方聚"}
     * }
     * {
     * "isDelete" : 0,
     * "orgPageId":"org_base_pageId",
     * "data": {"id":"12345","parentId":"00000","name":"地方聚","startDate":"*****","quota":"*****"}
     * }
     */
    /**
     * @param orgUpdateReq
     * @return com.bjsxt.corehr.pojo.vo.ResponseVO
     * @description: 插入和更新组织信息
     * @author: RenDeyou
     * @date: 2021/5/22 18:03
     */
    @ApiOperation(value = "editSaveOrgInfo", notes = "插入和更新组织信息")
    @PostMapping("/editSaveOrgInfo")
    public ResponseVO editSaveOrgInfo(@RequestBody OrgUpdateReq orgUpdateReq) {
        Map<String, Object> map = this.paramsCheck(orgUpdateReq);
        //校验不通过
        if (!(Boolean) map.get(StringEnum.FLAG.getStringName())) {
            return (ResponseVO) map.get(StringEnum.RESPONSE_VO.getStringName());
        }
        //校验通过
        OrgPageHandler orgPageHandler = (OrgPageHandler) map.get(StringEnum.ORG_PAGE_HANDLER.getStringName());
        return orgPageHandler.editSaveOrgInfo(orgUpdateReq);
    }

    /**
     * {
     * "isDelete":0,
     * "orgPageId":"org_communication_pageId",
     * "data": {"id":"12345","title":"组织交流主题2020"}
     * }
     * {
     * "isDelete":0,
     * "orgPageId":"org_location_pageId",
     * "data": {"id":"12345","environment":"组织驻地环境2020"}
     * }
     */
    /**
     * @param orgUpdateReq
     * @return com.bjsxt.corehr.pojo.vo.ResponseVO
     * @description: 插入组织其他信息
     * @author: RenDeyou
     * @date: 2021/5/22 18:03
     */
    @ApiOperation(value = "addOrgOtherInfo", notes = "插入组织其他信息")
    @PostMapping("/addOrgOtherInfo")
    public ResponseVO addOrgOtherInfo(@RequestBody OrgUpdateReq orgUpdateReq) {
        Map<String, Object> map = this.paramsCheck(orgUpdateReq);
        //校验不通过
        if (!(Boolean) map.get(StringEnum.FLAG.getStringName())) {
            return (ResponseVO) map.get(StringEnum.RESPONSE_VO.getStringName());
        }
        //校验通过
        OrgPageHandler orgPageHandler = (OrgPageHandler) map.get(StringEnum.ORG_PAGE_HANDLER.getStringName());
        return orgPageHandler.addOrgOtherInfo(orgUpdateReq);
    }

    /**
     * @param orgUpdateReq
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @description: 校验必填参数
     * @author: RenDeyou
     * @date: 2021/5/22 22:05
     */
    private Map<String, Object> paramsCheck(OrgUpdateReq orgUpdateReq) {
        Map<String, Object> map = Maps.newHashMap();
        //校验通过标识：true通过 false未通过
        Boolean flag = true;
        //前端返回值
        ResponseVO responseVO = new ResponseVO();

        //删除操作无需校验，非删除操作需要校验
        if (orgUpdateReq.getIsDelete() != 1) {
            //校验参数
            if (null == orgUpdateReq ||
                    StringUtils.isBlank(orgUpdateReq.getOrgPageId()) ||
                    null == orgUpdateReq.getData()) {
                flag = false;
                responseVO.setResponseCode(ResponseStatusEnum.CHECK_ERROR.getResponseCode());
                responseVO.setResponseMsg(ResponseStatusEnum.CHECK_ERROR.getResponseMsg());
            }
        }

        //获取组织信息集处理器类型
        OrgPageEnum orgPageEnum = OrgPageEnum.getOrgPageEnum(orgUpdateReq.getOrgPageId());
        if (null == orgPageEnum) {
            flag = false;
            responseVO.setResponseCode(ResponseStatusEnum.CHECK_ERROR.getResponseCode());
            responseVO.setResponseMsg("组织信息集处理器类型orgPageId不存在");
        }
        //获取组织信息集处理器
        OrgPageHandler orgPageHandler = OrgPageHandlerFactory.getOrgPageHandler(orgPageEnum);
        if (null == orgPageHandler) {
            flag = false;
            responseVO.setResponseCode(ResponseStatusEnum.CHECK_ERROR.getResponseCode());
            responseVO.setResponseMsg("组织信息集处理器类型OrgPageEnum不存在");
        }

        map.put(StringEnum.FLAG.getStringName(), flag);
        map.put(StringEnum.RESPONSE_VO.getStringName(), responseVO);
        map.put(StringEnum.ORG_PAGE_HANDLER.getStringName(), orgPageHandler);
        return map;
    }


}
