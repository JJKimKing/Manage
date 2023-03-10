package com.geeko.admin.reponse;

import com.geeko.admin.enums.ResultCode;

/**
 * @author JaneKim
 * @date 2023/3/9
 * @descript
 */
public class JResult {

    private static final long serialVersionUID = 1L;

    public final static JResult SUCCESS = new JResult(true, ResultCode.SUCCESS.getCode(), null);

    private boolean success;
    private int code; // 200：成功 300：未授权 400：校验失败 500：系统错误
    private Object result;

    public JResult() {

    }

    public JResult(boolean success, int code, Object result) {
        this.success = success;
        this.result = result;
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public static JResult createSuccess(Object result) {
        return new JResult(true, 200, result);
    }

    public static JResult createFail(int code, Object result) {
        return new JResult(false, code, result);
    }

    public static JResult createWarnMessage(Object result) {
        return createFail(ResultCode.WARN_MESSAGE.getCode(), result);
    }

    public static JResult create500Fail() {
        return createFail(500, "Internal server error");
    }


}
