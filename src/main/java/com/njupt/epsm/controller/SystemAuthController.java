package com.njupt.epsm.controller;

import com.njupt.epsm.common.CommonResult;
import com.njupt.epsm.common.UserInfo;
import com.njupt.epsm.req.*;
import com.njupt.epsm.service.SystemService;
import com.njupt.epsm.util.RestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("system")
@Api(tags = "登录及权限管理")
public class SystemAuthController {

    private static final Logger log = LoggerFactory.getLogger(SystemAuthController.class);

    @Autowired
    private SystemService systemService;


    @ApiModelProperty("登录")
    @PostMapping("user/login")
    public void login(HttpServletResponse response, @RequestBody LoginReq loginReq) {
        try {
            Validate.notBlank(loginReq.getLoginName(), "登录名不能为空");
            Validate.notBlank(loginReq.getPassword(), "密码不能为空");
            RestUtil.printData(response, systemService.login(loginReq.getLoginName(), loginReq.getPassword()));
        } catch (Exception e) {
            log.error("login error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("权限管理")
    @PostMapping("user/getUserInfo")
    public void getUserInfo(HttpServletResponse response,HttpServletRequest request) {
        try {
            Long userId = UserInfo.getUserId(request);
            RestUtil.printData(response, systemService.getUserInfo(userId));
        } catch (Exception e) {
            log.error("getUserInfo error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }
}
