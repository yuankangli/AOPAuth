package com.liyk.AOPAuth.framework.common.exception;

/***
 * 该异常将会导致前台页面跳转至登录页
 * 主要有以下情况 :
 *      １．未登录访问
 *      ２．重复登录，之前登陆的人会掉线
 *
 * @author liyk
 */
public class BackLoginException extends RuntimeException {

    public BackLoginException(String message) {
        super(message);
    }
}
