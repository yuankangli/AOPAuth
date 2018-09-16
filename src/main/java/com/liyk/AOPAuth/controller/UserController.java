package com.liyk.AOPAuth.controller;

import com.liyk.AOPAuth.entity.User;
import com.liyk.AOPAuth.framework.common.exception.SimpleException;
import com.liyk.AOPAuth.framework.common.listener.SessionListener;
import com.liyk.AOPAuth.framework.common.result.Result;
import com.liyk.AOPAuth.service.UserService;
import com.liyk.AOPAuth.util.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {


    final private
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public Result login(User user, HttpServletRequest request) {
        String password = user.getPassword() + "{" + user.getUsername() + "}";
        String resultPassword = Encrypt.md5AndSha(password);
        User daoUser = userService.findByusername(user.getUsername());
        /*
            获取用户权限
         */
        List<String> roleList = userService.getRoleList(user.getUsername());
        daoUser.setRoleList(roleList);
        if (daoUser != null && resultPassword.equals(daoUser.getPassword())) {
            // 将用户保存到 SessionListener 中
            SessionListener.addSession(request.getSession(), daoUser);
            daoUser.setPassword(null);
            return new Result(daoUser);
        } else {
            throw new SimpleException("账号或密码错误");
        }
    }

    @PostMapping
    public Result save(User user){
        // 判斷賬號是否重复
        User byusername = userService.findByusername(user.getUsername());
        if (byusername!= null) {
            throw new RuntimeException("账号重复");
        }
        // 密码加密
        String password = user.getPassword() + "{" + user.getUsername() + "}";
        String resultPassword = Encrypt.md5AndSha(password);
        user.setPassword(resultPassword);
        userService.save(user);
        return new Result("注册成功");
    }

    @PostMapping("logout")
    public Result logout(User user, HttpServletRequest request) {
        String id = request.getSession().getId();
        SessionListener.destroySession(id);
        return new Result("注销成功");
    }
}
