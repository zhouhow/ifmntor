package com.bjtu.common.result;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;
    private Result(){

    }
    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum){
        Result<T> result = new Result<>();
        if(body != null) result.setData(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    public static <T> Result<T> build(T body, Integer code, String message){
        Result<T> result = new Result<>();
        if(body != null) result.setData(body);
        result.setCode(code);
        result.setMessage(message);
        return result;

    }
    public static<T> Result<T> oK(){
        return build(null, ResultCodeEnum.SUCCESS);
    }
    public static<T> Result<T> oK(T data){
        return build(data, ResultCodeEnum.SUCCESS);
    }
    public static<T> Result<T> fail(){
        return build(null, ResultCodeEnum.FAIL);
    }
    public static<T> Result<T> fail(T data){
        return build(data, ResultCodeEnum.FAIL);
    }

    /*
    further expand
     */
    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }
}
