package com.violetfreesia.carnation.test.controller;

import com.violetfreesia.carnation.authenticate.Authenticator;
import com.violetfreesia.carnation.interceptor.annotation.CarnationAuth;
import com.violetfreesia.carnation.interceptor.annotation.CheckStringPermission;
import com.violetfreesia.carnation.interceptor.annotation.CheckStringRole;
import com.violetfreesia.carnation.support.Token;
import com.violetfreesia.carnation.support.UserInfo;
import com.violetfreesia.carnation.test.sercurity.CheckPermission;
import com.violetfreesia.carnation.test.sercurity.CheckRole;
import com.violetfreesia.carnation.test.sercurity.UserPermission;
import com.violetfreesia.carnation.test.sercurity.UserRole;
import com.violetfreesia.carnation.test.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author violetfreesia
 * @date 2021-06-19
 */
@RestController
@CarnationAuth
public class TestController {

    private final Authenticator authenticator;

    private final TestService testService;

    public TestController(Authenticator authenticator, TestService testService) {
        this.authenticator = authenticator;
        this.testService = testService;
    }

    @GetMapping("/public")
    public String needNon() {
        return "开放接口";
    }

    @GetMapping("/need-login")
    public UserInfo<?, ?> needLogin(UserInfo<?, ?> userInfo) {
        return userInfo;
    }

    @GetMapping("/reader")
    @CheckStringRole("reader")
    @CheckRole(UserRole.reader)
    public String reader() {
        return "仅限读者访问";
    }

    @GetMapping("/update")
    @CheckStringPermission("update")
    @CheckPermission(UserPermission.update)
    public String update() {
        return "仅限有更新权限的访问";
    }

    @GetMapping("reader-or-author")
    @CheckStringRole({"reader", "author"})
    @CheckRole({UserRole.reader, UserRole.author})
    public String readerOrAuthor() {
        return "读者或者作者可访问";
    }

    @GetMapping("reader-and-update")
    @CheckStringRole("reader")
    @CheckStringPermission("update")
    @CheckRole(UserRole.reader)
    @CheckPermission(UserPermission.update)
    public String authorAndUpdate() {
        return "具有更新权限的读者可访问";
    }

    @GetMapping("author-or-update")
    @CarnationAuth(authType = CarnationAuth.AuthType.OR)
    @CheckStringRole("author")
    @CheckStringPermission("update")
    @CheckRole(UserRole.author)
    @CheckPermission(UserPermission.update)
    public String authorOrUpdate() {
        return "作者或者具有更新权限可访问";
    }

    @GetMapping("/login")
    public Token login(@RequestParam String id) {
        return authenticator.login(testService.getUserInfo(id));
    }

    @GetMapping("/public/login2")
    public Token login2(@RequestParam String id) {
        return authenticator.login(testService.getAuthInfo(id));
    }

    @GetMapping("/logout")
    public boolean logout() {
        authenticator.logout();
        return true;
    }

    @GetMapping("/public/refresh-token")
    public Token refresh(@RequestParam String refreshToken) {
        return authenticator.updateToken(refreshToken, testService::getUserInfo);
    }

    @GetMapping("/public/refresh-token2")
    public Token refresh2(@RequestParam String refreshToken) {
        return authenticator.updateToken(refreshToken, testService::getAuthInfo);
    }
}
