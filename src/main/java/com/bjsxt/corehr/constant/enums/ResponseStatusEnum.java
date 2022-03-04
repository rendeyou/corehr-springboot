package com.bjsxt.corehr.constant.enums;

/**
 * @className: ResponseStatusEnum
 * @description: 前端返回值状态枚举类
 * @author: RenDeYou
 * @date: 2021/4/18 22:52
 */
public enum ResponseStatusEnum {
    OK("100001", "OK", "请求成功"),
    ERROR("100002", "ERROR", "请求失败"),
    CHECK_OK("100003", "OK", "参数校验成功"),
    CHECK_ERROR("100004", "ERROR", "参数校验失败");

    private String responseCode;
    private String responseMsg;
    private String responseDesc;

    ResponseStatusEnum(String responseCode, String responseMsg, String responseDesc) {
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
        this.responseDesc = responseDesc;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public String getResponseDesc() {
        return responseDesc;
    }
}
