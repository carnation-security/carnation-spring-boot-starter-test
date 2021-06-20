package com.violetfreesia.carnation.test.sercurity;

import com.violetfreesia.carnation.support.Role;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author violetfreesia
 * @date 2021-06-19
 */
public enum UserRole implements Role<UserRole, UserPermission> {
    /**
     * 用户角色
     */
    reader,
    manager,
    author;

    private final Set<UserPermission> permissions;

    UserRole(UserPermission... permissions) {
        this.permissions = new HashSet<>();
        Collections.addAll(this.permissions, permissions);
    }

    /**
     * 获取角色的值
     *
     * @return 返回角色值
     */
    @Override
    public UserRole getValue() {
        return this;
    }

    /**
     * 获取角色具备的权限
     *
     * @return 权限集合
     */
    @Override
    public Set<UserPermission> getPermissions() {
        return this.permissions;
    }
}
