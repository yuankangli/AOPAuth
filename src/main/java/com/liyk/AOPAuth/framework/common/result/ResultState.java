package com.liyk.AOPAuth.framework.common.result;

/**
 * @author liyk
 * @date 2018-04-06 下午 7:10
 */
public class ResultState {
    /**
     * 成功
     */
    public static final int SUCCESS = 200;

    /**
     * 未登录异常,可能原因:未登录/已在他处登陆,登录信息超时
     * 该异常返回给前台后会退回登录页面
     */
    public static final int BACKLOGINEXCEPTION = 901;
    /**
     * 自定义的异常
     */
    public static final int SIMPLEEXCEPTION = 902;

    /**
     * 未处理的运行时异常
     */
    public static final int RUNTIMEEXCEPTION = 900;

}
