package com.liyk.AOPAuth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/***
 *  用户权限表
 *  @author liyk
 */
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 权限名称
     */
    private String roleName;
    /**
     *  标记权限类型
     *  1: 页面权限
     *  2: 控件权限
     */
    private Integer falg;
    /**
     * 控件对应的页面权限 id, 主要用于树形体现
     */
    private Long parnetRoleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getFalg() {
        return falg;
    }

    public void setFalg(Integer falg) {
        this.falg = falg;
    }

    public Long getParnetRoleId() {
        return parnetRoleId;
    }

    public void setParnetRoleId(Long parnetRoleId) {
        this.parnetRoleId = parnetRoleId;
    }

    // 重写 equals 方法

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
