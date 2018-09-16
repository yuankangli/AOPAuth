package com.liyk.AOPAuth.util;


import com.liyk.AOPAuth.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class UserUtil {

    public static String getUserName(){
        User user= getUser();
        if (user == null) {
            return "admin";
        }
        return user.getUsername();
    }
    public static User getUser(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            User user= (User)request.getSession().getAttribute("currUser");
            return user;
        }
        return null;
    }
}
