package cn.shineiot.base.module;

import java.util.List;

/**
 * 所有javabean的基类，包含返回的公共属性
 * <p/>
 * BaseEvent
 * <p/>
 * 2014-12-9 下午2:40:40
 *
 * @version 1.0.0
 */

public class BaseListResult<T> {

    private static final long serialVersionUID = 1L;
    private String errorMsg ;
    private int errorCode ;
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
