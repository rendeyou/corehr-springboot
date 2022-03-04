package com.bjsxt.corehr.constant.enums;

/**
 * @className: OrgPageEnum
 * @description: 组织信息集处理器类型枚举类
 * @author: RenDeYou
 * @date: 2021/4/15 18:36
 */
public enum OrgPageEnum {

    ORG_BASE_ENUM("org_base_pageId", "table_org", "组织信息表"),
    ORG_LOCATION_ENUM("org_location_pageId", "table_location", "组织驻地信息表"),
    ORG_COMMUNICATION_ENUM("org_communication_pageId", "table_communication", "组织交流信息表"),
    ORG_TEST_ENUM("org_test_pageId", "table_test", "组织测试信息表");

    private String orgPageId;
    private String orgTableName;
    private String orgTableDesc;

    OrgPageEnum(String orgPageId, String orgTableName, String orgTableDesc) {
        this.orgPageId = orgPageId;
        this.orgTableName = orgTableName;
        this.orgTableDesc = orgTableDesc;
    }

    public String getOrgPageId() {
        return orgPageId;
    }

    public String getOrgTableName() {
        return orgTableName;
    }

    public String getOrgTableDesc() {
        return orgTableDesc;
    }

    /**
     * 根据属性（orgPageId）获取枚举（OrgPageEnum）
     */
    public static OrgPageEnum getOrgPageEnum(String orgPageId) {
        OrgPageEnum[] values = OrgPageEnum.values();
        for (OrgPageEnum orgPageEnum : values) {
            if (orgPageEnum.getOrgPageId().equals(orgPageId)) {
                return orgPageEnum;
            }
        }
        return null;
    }
}
