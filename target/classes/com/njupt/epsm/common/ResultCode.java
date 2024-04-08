package com.njupt.epsm.common;

/**
 * @author qiuhuajie
 * @date 2022/10/06
 */
public enum ResultCode implements IErrorCode {
    //SUCCESS
    SUCCESS(200, "操作成功"),

    CREATED(201, "重复请求"),
    //FAILED
    FAILED(500, "操作失败"),
    //LOGIN_ERROR
    LOGIN_ERROR(402, "访问异常平台"),
    //VALIDATE_FAILED
    VALIDATE_FAILED(404, "参数检验失败"),
    //UNAUTHORIZED
    UNAUTHORIZED(401, "未授权"),

    REFRESH_TOKEN(403, "暂未登录或token已经过期"),;
    /**
     * code
     */
    private final long code;
    /**
     * message
     */
    private final String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
    
}
