package com.bjtu.common.handler;

import com.bjtu.common.result.Result;
import com.bjtu.common.result.ResultCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * spring security异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result error(AccessDeniedException e) throws AccessDeniedException {
        return Result.build(null, ResultCodeEnum.PERMISSION);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail().message("执行了全局异常处理");
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Result errorNull(Exception e) {
        e.printStackTrace();
        return Result.fail().message("执行了空指针异常处理");
    }

    @ExceptionHandler(BJTUException.class)
    @ResponseBody
    public Result errorBJTU(BJTUException e) {
        e.printStackTrace();
        return Result.fail().message("Server error");
    }
}
