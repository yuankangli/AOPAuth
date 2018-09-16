package com.liyk.AOPAuth.framework.common.aspect;

import com.liyk.AOPAuth.entity.User;
import com.liyk.AOPAuth.framework.common.annotation.RequireAtuh;
import com.liyk.AOPAuth.framework.common.exception.BackLoginException;
import com.liyk.AOPAuth.framework.common.exception.SimpleException;
import com.liyk.AOPAuth.framework.common.result.ResultMsg;
import com.liyk.AOPAuth.util.UserUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class AuthAspect {

    @Pointcut("@annotation(com.liyk.AOPAuth.framework.common.annotation.RequireAtuh)")
    public void annotationPoint() {
    }

    @Before("annotationPoint()")
    public void beforeAnnotation(JoinPoint joinPoint){
        MethodSignature signature=(MethodSignature) joinPoint.getSignature();
        Method method=signature.getMethod();
        RequireAtuh action=method.getAnnotation(RequireAtuh.class);
        String[] roleStrs = action.value();
        System.out.println("方法需要的权限为："+ Arrays.toString(roleStrs));
        // 如果没有内容, 默认所有人都有权限操作
        if (roleStrs.length > 0) {
            // 判断权限类型
        }
        User user = UserUtil.getUser();
        if (user == null) {
            throw new BackLoginException(ResultMsg.NOLOGIN);
        }
        List<String> roleList = user.getRoleList();
        boolean hasRole = false;
        if (roleList != null && roleList.size() != 0) {
            for (String s : roleStrs) {
                if (roleList.contains(s)) {
                    hasRole = true;
                    break;
                }
            }
        }
        if (!hasRole) {
            throw new SimpleException(ResultMsg.NOAUTH);
        }
    }
}
