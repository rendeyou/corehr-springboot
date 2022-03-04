package com.bjsxt.corehr.pojo.po.org;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: Org
 * @description: 组织信息
 * @author: RenDeYou
 * @date: 2021/4/15 18:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Org implements Serializable {
    private static final long serialVersionUID = -4192392401103973356L;
    private String id;
    private String parentId;
    private String name;
    private Date startDate;
    /**
     * 编制配额
     */
    private Integer quota;
}
