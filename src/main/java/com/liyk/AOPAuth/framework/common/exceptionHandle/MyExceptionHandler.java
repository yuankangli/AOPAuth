package com.liyk.AOPAuth.framework.common.exceptionHandle;


import com.liyk.AOPAuth.framework.common.exception.BackLoginException;
import com.liyk.AOPAuth.framework.common.exception.SimpleException;
import com.liyk.AOPAuth.framework.common.result.Result;
import com.liyk.AOPAuth.framework.common.result.ResultState;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liyk
 * @date 2018-04-06 下午 6:23
 */

@ControllerAdvice
public class MyExceptionHandler {

    /**
     * 处理未登录异常
     * @return Result
     */
    @ExceptionHandler({BackLoginException.class})
    @ResponseBody
    public Result backLoginException(BackLoginException backLoginException){
        return new Result(false, ResultState.BACKLOGINEXCEPTION, backLoginException.getLocalizedMessage());
    }

    /**
     * 处理其他不需要页面跳转的异常
     * @return Result
     */
    @ExceptionHandler({SimpleException.class})
    @ResponseBody
    public Result simpleException(SimpleException simpleException){
        return new Result(false, ResultState.SIMPLEEXCEPTION, simpleException.getLocalizedMessage());
    }
    /**
     * 未捕获的异常 和 自定义的异常
     * @return Result
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result runtimeException(RuntimeException runtimeException){
        Class<? extends RuntimeException> aClass = runtimeException.getClass();
        String packageName = aClass.getName();
        System.out.println("未被处理的异常:"+ packageName);
        System.out.println(getTraceInfo(runtimeException));
        String localizedMessage = "<div>异常原因:</div>"+runtimeException+"<div>详细信息</div>"+runtimeException.getLocalizedMessage();
        System.out.println(localizedMessage);
        return new Result(false, ResultState.RUNTIMEEXCEPTION,localizedMessage);
    }

    private static String getTraceInfo(RuntimeException runtimeException) {
        StringBuilder stringBuilder = new StringBuilder();
        StackTraceElement[] stacks = runtimeException.getStackTrace();
        for (StackTraceElement stack : stacks) {
            stringBuilder.append("Exception in [class: ")
                    .append(stack.getClassName()).append("][method: ")
                    .append(stack.getMethodName()).append("][line: ")
                    .append(stack.getLineNumber()).append("]\r\n");
        }
        return stringBuilder.toString();
    }
}
