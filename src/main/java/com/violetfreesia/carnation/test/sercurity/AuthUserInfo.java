package com.violetfreesia.carnation.test.sercurity;

import com.violetfreesia.carnation.support.Permission;
import com.violetfreesia.carnation.support.Role;
import com.violetfreesia.carnation.support.UserInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author violetfreesia
 * @date 2021-06-19
 */
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthUserInfo implements UserInfo<UserRole, UserPermission> {

    private String userId;

    private UserRole role;

    private Set<UserPermission> permissions;

    public AuthUserInfo(String userId, UserRole role) {
        this.userId = userId;
        this.role = role;
        this.permissions = Collections.emptySet();
    }

    public AuthUserInfo(String userId, UserRole role, UserPermission... permissions) {
        this.userId = userId;
        this.role = role;
        this.permissions = new HashSet<>();
        Collections.addAll(this.permissions, permissions);
    }

    /**
     * 获取用户具有的权限
     *
     * @return 权限集合
     */
    @Override
    public Set<? extends Permission<UserPermission>> getPermissions() {
        return this.permissions;
    }

    /**
     * 获取用户角色
     *
     * @return 用户角色
     */
    @Override
    public Role<UserRole, UserPermission> getRole() {
        return this.role;
    }

    /**
     * 获取用户唯一标识
     *
     * @return 用户唯一标识
     */
    @Override
    public String getUserId() {
        return this.userId;
    }
}
