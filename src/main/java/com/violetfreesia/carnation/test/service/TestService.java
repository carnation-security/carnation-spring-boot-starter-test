package com.violetfreesia.carnation.test.service;

import com.violetfreesia.carnation.support.DefaultUserInfo;
import com.violetfreesia.carnation.support.StringPermission;
import com.violetfreesia.carnation.support.StringRole;
import com.violetfreesia.carnation.test.sercurity.AuthUserInfo;
import com.violetfreesia.carnation.test.sercurity.UserPermission;
import com.violetfreesia.carnation.test.sercurity.UserRole;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author violetfreesia
 * @date 2021-06-19
 */
@Service
public class TestService {

    private final Map<String, StringPermission> permissionMap = new HashMap<>(4);
    private final Map<String, StringRole> roleMap = new HashMap<>(3);

    {
        permissionMap.put("create", new StringPermission("create"));
        permissionMap.put("read", new StringPermission("read"));
        permissionMap.put("update", new StringPermission("update"));
        permissionMap.put("delete", new StringPermission("delete"));
        roleMap.put("reader", new StringRole("reader", permissionMap.get("read")));
        roleMap.put("author", new StringRole("author", permissionMap.get("read"),
                permissionMap.get("create"), permissionMap.get("update")));
        roleMap.put("manager", new StringRole("manager", permissionMap.get("read"),
                permissionMap.get("create"), permissionMap.get("update"), permissionMap.get("delete")));
    }

    public DefaultUserInfo getUserInfo(Serializable id) {
        String s = id instanceof String ? (String) id : "";
        switch (s) {
            case "1":
                return new DefaultUserInfo(id, roleMap.get("manager"));
            case "2":
                return new DefaultUserInfo(id, roleMap.get("author"));
            case "3":
                return new DefaultUserInfo(id, roleMap.get("reader"), permissionMap.get("update"));
            default:
                return new DefaultUserInfo(id, roleMap.get("reader"));
        }
    }

    public AuthUserInfo getAuthInfo(Serializable id) {
        String s = id instanceof String ? (String) id : "";
        switch (s) {
            case "1":
                return new AuthUserInfo(s, UserRole.manager);
            case "2":
                return new AuthUserInfo(s, UserRole.author);
            case "3":
                return new AuthUserInfo(s, UserRole.reader, UserPermission.update);
            default:
                return new AuthUserInfo(s, UserRole.reader);
        }
    }
}
