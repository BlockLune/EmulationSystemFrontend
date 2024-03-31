package com.njupt.epsm.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 共同结果
 * 通用返回对象
 *
 * @author cpm
 * @date 2022/10/06  10:38:40
 */

@Data
public class CommonResult<T> implements Serializable {
    /**
     * 状态码
     */
    public long code;
    /**
     * 提示信息
     */
    public String message;
    /**
     * 数据封装
     */
    public T data;

    public CommonResult() {
    }

    public CommonResult(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> CommonResult<T> success() {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }


    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 重复请求
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> CommonResult<T> repetition(T data, String message) {
        return new CommonResult<T>(ResultCode.CREATED.getCode(), message, data);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     * @param message   错误信息
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode, String message) {
        return new CommonResult<T>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 自定义异常错误码
     * @param message   错误信息
     */
    public static <T> CommonResult<T> failed(long errorCode, String message) {
        return new CommonResult<T>(errorCode, message, null);
    }


    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), message, null);
    }
//
//    public static <T> CommonResult<T> loginFailed(T data) {
//        return new CommonResult<T>(ResultCode.LOGIN_ERROR.getCode(), ResultCode.LOGIN_ERROR.getMessage(), data);
//    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
//    public static <T> CommonResult<T> validateFailed(String message) {
//        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
//    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T data, String message) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), message, data);
    }

    public static <T> CommonResult<T> refreshToken(T data, String message) {
        return new CommonResult<T>(ResultCode.REFRESH_TOKEN.getCode(), message, data);
    }

    /**
     *
     */
    public static boolean isSuccess(CommonResult commonResult) {
        return commonResult.getCode() == ResultCode.SUCCESS.getCode();
    }

    /**
     * 未授权返回结果
     */
//    public static <T> CommonResult<T> forbidden(T data) {
//        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
//    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }


    public void setData(T data) {
        this.data = data;
    }
}
