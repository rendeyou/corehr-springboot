package com.bjsxt.corehr.pojo.po.elasticSearch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: OrgEsIndexData
 * @description: 组织ES返回值类型
 * @author: RenDeYou
 * @date: 2021/4/19 15:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrgEsIndexData implements Serializable {

    private String id;
    private String parentId;
    private String name;
    private Date startDate;
    /**
     * 编制配额
     */
    private Integer quota;
    /**
     * 交流主题
     */
    private String title;
    /**
     * 驻地环境
     */
    private String environment;
}
