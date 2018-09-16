package com.liyk.AOPAuth.service;

import com.liyk.AOPAuth.entity.Role;
import com.liyk.AOPAuth.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {

    User findByusername(String username);

    void save(User user);

    List<String> getRoleList(String username);
}
