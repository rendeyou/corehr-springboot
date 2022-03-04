package com.bjsxt.corehr.pojo.req.org;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @className: OrgUpdateReq
 * @description: 组织信息集增删改请求参数实体类
 * @author: RenDeYou
 * @date: 2021/4/15 21:16
 */
@ApiModel(value = "OrgUpdateReq", description = "组织信息集增删改请求参数实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrgUpdateReq {
    /**
     * 删除操作标识：1删除操作 0非删除操作
     */
    private Short isDelete;

    /**
     * 组织信息集处理器类型orgPageId
     */
    private String orgPageId;

    /**
     * 请求参数
     */
    private Map<String, Object> data;
}
