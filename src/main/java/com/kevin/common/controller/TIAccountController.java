package com.kevin.common.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.kevin.auth.JWTValidation;
import com.kevin.common.config.AccountConstant;
import com.kevin.common.domain.TIAccount;
import com.kevin.common.service.ITIAccountService;
import com.kevin.common.service.UserService;
import com.kevin.common.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.kevin.auth.TokenUtil.token;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kevin
 * @since 2021-12-20
 */
@Api(tags = "账号管理")
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class TIAccountController {

    private final ITIAccountService service;
    private final UserService userService;

    @GetMapping("login")
    @ApiOperation(value = "登录")
    public R sign(TIAccount account){
        int count = service.count(Wrappers.<TIAccount>lambdaQuery()
                .eq(TIAccount::getAccount, account.getAccount())
                .eq(TIAccount::getPassword, MD5Util.MD5(account.getPassword())));

        if (count!=1){
            return R.failed("账号或密码错误!");
        }

        return R.ok(token(account.getAccount(),account.getName(),account.getAccountType()+""));
    }

    @JWTValidation(AccountConstant.ADMIN)
    @GetMapping("update")
    @ApiOperation(value = "更新账号信息")
    public R update(TIAccount account){
        TIAccount old = service.getById(account.getId());
        service.updateById(old
                .setAccountType(account.getAccountType())
                .setName(account.getName())
                .setPassword(MD5Util.MD5(account.getPassword()))
                .setUpdateAccount(userService.getAccount())
                .setUpdateTime(LocalDateTime.now()));
        return R.ok("");
    }
}

