package com.liyk.AOPAuth.framework.common.aspect;

import com.liyk.AOPAuth.framework.common.exception.BackLoginException;
import com.liyk.AOPAuth.framework.common.exception.SimpleException;
import com.liyk.AOPAuth.framework.common.listener.SessionListener;
import com.liyk.AOPAuth.framework.common.result.ResultMsg;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author liyk
 * @date 2018-04-06 下午 4:22
 */
@Aspect
@Component
//@Scope("session")
//@Profile("prod")
public class LoginAspect {


    private final HttpSession session;

    @Autowired
    public LoginAspect(HttpSession session) {
        this.session = session;
    }

    @Pointcut("execution(public * com.liyk..*Controller.*(..)) "
            + " && !execution(public * com.liyk..UserController.*(..))"
            + " && !execution(public * com.liyk..IndexController.*(..))")
    public void pointCutMethod() {
    }

    @Before("pointCutMethod()")
    public void before(JoinPoint joinpoint) {
//        System.out.println("进入切面!");
        HashMap<String, HttpSession> list = SessionListener.getList();
        if (!list.containsKey(session.getId())) {
            throw new BackLoginException(ResultMsg.NOLOGIN);
        } else if (session.getAttribute("beLogined") != null) {
            // 如果当前session标记为被踢出,抛出已在他处登录异常
            // 此处直接返回已在他出登录异常,将监听器中的session删除
            list.remove(session.getId());
            System.out.println(list.size());
            //销毁session,下次登录重新生成新的session
            session.invalidate();
            throw new BackLoginException(ResultMsg.BELOGIN);
        }
    }
}
