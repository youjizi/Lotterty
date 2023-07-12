package com.libai.lottery.common;

import java.io.Serializable;

/**
 * @description: 统一返回类， Code码，Info描述
 * @author： 有骥子
 * @date: 2023/7/1
 */
public class Result implements Serializable {

    private static final long serialVersionUID = -3826891916021780628L;
    private String code;
    private String info;


    public static Result buildResult(String code, String info) {
        return new Result(code, info);
    }

    public static Result buildResult(Constants.ResponseCode state, String info) {

        return new Result(state.getCode(), info);
    }


    public static Result buildSuccessResult(String info) {
        return new Result(Constants.ResponseCode.SUCCESS.getCode(),info);
    }

    public static Result buildSuccessResult() {
        return new Result(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo());
    }


    public static Result buildErrorResult(String info) {
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(), info);
    }

    public static Result buildErrorResult() {
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    public Result(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


}
