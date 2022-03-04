package com.bjsxt.corehr.pojo.po.org;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @className: OrgLocation
 * @description: 组织驻地信息
 * @author: RenDeYou
 * @date: 2021/4/15 18:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrgLocation implements Serializable {
    private static final long serialVersionUID = -952030238227533668L;
    private String id;
    /**
     * 驻地环境
     */
    private String environment;
}
