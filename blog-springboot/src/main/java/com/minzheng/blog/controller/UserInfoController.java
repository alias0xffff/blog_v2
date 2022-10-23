package com.minzheng.blog.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.LineCaptcha;
import com.minzheng.blog.annotation.OptLog;
import com.minzheng.blog.service.RedisService;
import com.minzheng.blog.vo.PageResult;
import com.minzheng.blog.dto.UserOnlineDTO;
import com.minzheng.blog.service.UserInfoService;
import com.minzheng.blog.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.minzheng.blog.constant.OptTypeConst.UPDATE;
import static com.minzheng.blog.constant.RedisPrefixConst.CODE_EXPIRE_TIME;

/**
 * 用户信息控制器
 *
 * @author yezhiqiu
 * @date 2021/07/29
 */
@Api(tags = "用户信息模块")
@RestController
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RedisService redisService;

    /**
     * 更新用户信息
     *
     * @param userInfoVO 用户信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "更新用户信息")
    @PutMapping("/users/info")
    public Result<?> updateUserInfo(@Valid @RequestBody UserInfoVO userInfoVO) {
        userInfoService.updateUserInfo(userInfoVO);
        return Result.ok();
    }

    /**
     * 更新用户头像
     *
     * @param file 文件
     * @return {@link Result<String>} 头像地址
     */
    @ApiOperation(value = "更新用户头像")
    @ApiImplicitParam(name = "file", value = "用户头像", required = true, dataType = "MultipartFile")
    @PostMapping("/users/avatar")
    public Result<String> updateUserAvatar(MultipartFile file) {
        return Result.ok(userInfoService.updateUserAvatar(file));
    }

    /**
     * 绑定用户邮箱
     *
     * @param emailVO 邮箱信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "绑定用户邮箱")
    @PostMapping("/users/email")
    public Result<?> saveUserEmail(@Valid @RequestBody EmailVO emailVO) {
        userInfoService.saveUserEmail(emailVO);
        return Result.ok();
    }

    /**
     * 修改用户角色
     *
     * @param userRoleVO 用户角色信息
     * @return {@link Result<>}
     */
    @OptLog(optType = UPDATE)
    @ApiOperation(value = "修改用户角色")
    @PutMapping("/admin/users/role")
    public Result<?> updateUserRole(@Valid @RequestBody UserRoleVO userRoleVO) {
        userInfoService.updateUserRole(userRoleVO);
        return Result.ok();
    }

    /**
     * 修改用户禁用状态
     *
     * @param userDisableVO 用户禁用信息
     * @return {@link Result<>}
     */
    @OptLog(optType = UPDATE)
    @ApiOperation(value = "修改用户禁用状态")
    @PutMapping("/admin/users/disable")
    public Result<?> updateUserDisable(@Valid @RequestBody UserDisableVO userDisableVO) {
        userInfoService.updateUserDisable(userDisableVO);
        return Result.ok();
    }

    /**
     * 查看在线用户
     *
     * @param conditionVO 条件
     * @return {@link Result<UserOnlineDTO>} 在线用户列表
     */
    @ApiOperation(value = "查看在线用户")
    @GetMapping("/admin/users/online")
    public Result<PageResult<UserOnlineDTO>> listOnlineUsers(ConditionVO conditionVO) {
        return Result.ok(userInfoService.listOnlineUsers(conditionVO));
    }

    /**
     * 下线用户
     *
     * @param userInfoId 用户信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "下线用户")
    @DeleteMapping("/admin/users/{userInfoId}/online")
    public Result<?> removeOnlineUser(@PathVariable("userInfoId") Integer userInfoId) {
        userInfoService.removeOnlineUser(userInfoId);
        return Result.ok();
    }

    @ApiOperation(value = "获取图片验证码")
    @GetMapping("/getVerifyCode")
    public Result<?> getLoginVerifyImage(HttpServletRequest request, HttpServletResponse response) {
        String imageBase64 = "";
        Map<String, String> result = new HashMap<>();
        try {
            LineCaptcha captcha = CaptchaUtil.createLineCaptcha(200, 100, 4, 300);
            response.setContentType("image/jpeg");
            response.setHeader("Pragma", "No-cache");
            imageBase64 = "data:image/png;base64," + captcha.getImageBase64();
            String code = captcha.getCode();
            String codeKey = UUID.randomUUID().toString();
            redisService.set(codeKey, code, CODE_EXPIRE_TIME);
            result.put("code", imageBase64);
            result.put("codeKey", codeKey);
        } catch (Exception e) {
            return Result.fail();
        }
        return Result.ok(result);
    }

}

