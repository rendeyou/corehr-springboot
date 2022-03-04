package com.bjsxt.corehr.constant.enums;

/**
 * @className: StringEnum
 * @description: 字符串枚举类
 * @author: RenDeYou
 * @date: 2021/4/18 22:52
 */
public enum StringEnum {
    DESC("desc", "描述"),
    ORG_PAGE_HANDLER("orgPageHandler", "组织信息集处理器"),
    RESPONSE_VO("responseVO", "前端返回值"),
    FLAG("flag", "标识");

    private String stringName;
    private String stringDesc;

    StringEnum(String stringName, String stringDesc) {
        this.stringName = stringName;
        this.stringDesc = stringDesc;
    }

    public String getStringName() {
        return stringName;
    }

    public String getStringDesc() {
        return stringDesc;
    }
}
