package com.bjsxt.corehr.pojo.vo;

import com.bjsxt.corehr.constant.enums.ResponseStatusEnum;
import org.springframework.ui.ModelMap;

import java.io.Serializable;

/**
 * @className: ResponseVO
 * @description: ResponseVO
 * @author: RenDeYou
 * @date: 2021/4/15 20:22
 */
public class ResponseVO extends ModelMap implements Serializable {

    private static final long serialVersionUID = -7111723281682276691L;

    public ResponseVO() {
        this.setResponseCode(ResponseStatusEnum.OK.getResponseCode());
        this.setResponseMsg(ResponseStatusEnum.OK.getResponseMsg());
    }

    public ResponseVO(String responseCode) {
        this.setResponseCode(responseCode);
    }

    public ResponseVO(String responseCode, String responseMsg) {
        this.setResponseCode(responseCode);
        this.setResponseMsg(responseMsg);
    }

    public ResponseVO(String responseCode, String responseMsg, Exception exception) {
        this.setResponseCode(responseCode);
        this.setResponseMsg(responseMsg);
        this.setException(exception);
    }

    public String getResponseCode() {
        return (String) this.get("responseCode");
    }

    public ResponseVO setResponseCode(String responseCode) {
        this.put("responseCode", responseCode);
        return this;
    }

    public String getResponseMsg() {
        return (String) this.get("responseMsg");
    }

    public ResponseVO setResponseMsg(String responseMsg) {
        this.put("responseMsg", responseMsg);
        return this;
    }

    public Object getData() {
        return this.get("data");
    }

    public ResponseVO setData(Object data) {
        this.put("data", data);
        return this;
    }

    public String getException() {
        return (String) this.get("exception");
    }

    public ResponseVO setException(Exception exception) {
        String exOut = "";
        StackTraceElement[] stackTraceElements = exception.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            exOut = exOut + "\t" + stackTraceElement + "\r\n";
        }
        this.put("exception", exOut);
        return this;
    }
}
