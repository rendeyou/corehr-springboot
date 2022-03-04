package com.bjsxt.corehr.pojo.po.org;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @className: OrgCommunication
 * @description: 组织交流信息
 * @author: RenDeYou
 * @date: 2021/4/15 18:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrgCommunication implements Serializable {
    private static final long serialVersionUID = -4191018296880228221L;
    private String id;
    /**
     * 交流主题
     */
    private String title;
}
