package com.liyk.AOPAuth.service.impl;

import com.liyk.AOPAuth.entity.Role;
import com.liyk.AOPAuth.entity.User;
import com.liyk.AOPAuth.repository.UserRepository;
import com.liyk.AOPAuth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    final
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByusername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<String> getRoleList(String username) {
        return userRepository.getRoleList(username);
    }
}
