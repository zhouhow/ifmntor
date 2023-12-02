package com.bjtu.common.handler;

import com.bjtu.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * 自定义全局异常类
 *
 */
@Data
public class BJTUException extends RuntimeException{
    private Integer code;
    private String message;

    /**
     * 通过状态码和错误消息创建异常对象
     */
    public BJTUException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 接收枚举类型对象
     */
    public BJTUException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "BJTUException{" +
                "code=" + this.getCode() +
                ", message=" + this.getMessage() +
                '}';
    }
}
