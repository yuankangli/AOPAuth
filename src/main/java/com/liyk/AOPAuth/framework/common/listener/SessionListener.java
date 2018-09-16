package com.liyk.AOPAuth.framework.common.listener;

import com.liyk.AOPAuth.entity.User;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;


@WebListener
public class SessionListener implements HttpSessionListener {

    private static HashMap<String, HttpSession> list = new HashMap<>();

    public static HashMap<String, HttpSession> getList() {
        return list;
    }

    /**
     *  Session创建事件,
     * */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
//        HttpSession session = se.getSession();
//        String id = session.getId();
//        list.put(id,session);
    }

    /**
     *  Session失效事件
     *  */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session失效了....");
        HttpSession session = se.getSession();
        String id = session.getId();
        list.remove(id);
        System.out.println("当前系统在线人数为:"+getUserCount());
    }

    public static void destroySession(String id){
        System.out.println("用户登出操作....");
        list.remove(id);
        System.out.println("当前系统在线人数为:"+getUserCount());
    }

    public static void addSession(HttpSession session, String username){
        //判断 SessionListener 中是否存在当前用户,如果存在,踢出上一位登录用户
        for (Map.Entry<String, HttpSession> sessionEntry : list.entrySet()) {
            HttpSession value = sessionEntry.getValue();
            if(username.equals(value.getAttribute("currUser"))){
                String key = sessionEntry.getKey();
                value.setAttribute("beLogined",true);
            }
        }
        //设置session失效时间,单位:秒
        session.setMaxInactiveInterval(30*60);
        session.setAttribute("currUser",username);
        list.put(session.getId(),session);
    }
    
    public static void addSession(HttpSession session, User username){
        //判断 SessionListener 中是否存在当前用户,如果存在,踢出上一位登录用户
        for (Map.Entry<String, HttpSession> sessionEntry : list.entrySet()) {
            HttpSession value = sessionEntry.getValue();
            User a=(User) value.getAttribute("currUser");
            //判断 session 中是否已存在同名 name
            boolean beLogined = username.getUsername().equals(a.getUsername());
            //判断同名 name 是否是同一个浏览器登录
            beLogined = beLogined && !session.getId().equals(sessionEntry.getKey());
            if(beLogined){
                String key = sessionEntry.getKey();
                value.setAttribute("beLogined",true);
            }
        }
        //设置session失效时间,单位:秒
        session.setMaxInactiveInterval(400*60);
        session.setAttribute("currUser",username);
        list.put(session.getId(),session);
        System.out.println("当前系统在线人数为:"+getUserCount());
    }

    public static void deleteSession(HttpSession session){
        String id = session.getId();
        list.remove(id);
        System.out.println("当前系统在线人数为:"+getUserCount());
    }

    public static int getUserCount(){
        int result = 0;
        for (Map.Entry<String, HttpSession> sessionEntry : list.entrySet()) {
            HttpSession value = sessionEntry.getValue();
            if(value.getAttribute("beLogined") == null){
                result++ ;
            }
        }
        return result;
    }
}