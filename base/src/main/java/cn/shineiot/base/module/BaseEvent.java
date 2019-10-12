package cn.shineiot.base.module;

import java.io.Serializable;

/**
 * 所有javabean的基类，包含返回的公共属性
 * <p/>
 * BaseEvent
 * <p/>
 * 2014-12-9 下午2:40:40
 *
 * @version 1.0.0
 */

public class BaseEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String message ;
    protected Boolean success;
    private String code ;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
