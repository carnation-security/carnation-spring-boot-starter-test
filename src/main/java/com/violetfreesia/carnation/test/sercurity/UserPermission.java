package com.violetfreesia.carnation.test.sercurity;

import com.violetfreesia.carnation.support.Permission;

/**
 * @author violetfreesia
 * @date 2021-06-19
 */
public enum UserPermission implements Permission<UserPermission> {
    /**
     * 权限
     */
    create,
    reade,
    update,
    delete;

    /**
     * 获取权限的值
     *
     * @return 返回权限值
     */
    @Override
    public UserPermission getValue() {
        return this;
    }
}
