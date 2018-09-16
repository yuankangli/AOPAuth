package com.liyk.AOPAuth.repository;

import com.liyk.AOPAuth.entity.Role;
import com.liyk.AOPAuth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 通过用户名查找User
     * @param username 用户名
     * @return 精准匹配的用户
     */
    User findByUsername(String username);

    @Query(value = "select b.role_name from user_role a, role b " +
            "where a.role_id = b.id and a.username = ?1"
            ,nativeQuery = true)
    List<String> getRoleList(String username);


}