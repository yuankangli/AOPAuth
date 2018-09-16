package com.liyk.AOPAuth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/***
 * 角色 - 权限 对照表
 * @author liyk
 */
@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色代号
     */
    private String username;

    /**
     * 权限id
     */
    private Long roleId;

}
