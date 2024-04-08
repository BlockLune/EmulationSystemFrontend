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
@Api(tags = "用户管理")
public class SystemUserController {

    private static final Logger log = LoggerFactory.getLogger(SystemUserController.class);

    @Autowired
    private SystemService systemService;

    @ApiModelProperty("创建用户")
    @PostMapping("user/createUser")
    public void createUser(HttpServletResponse response, @RequestBody CreateUserReq createUserReq) {
        try {
            Validate.notNull(createUserReq.getRoleId(), "角色ID不能为空");
            Validate.notBlank(createUserReq.getUserName(), "用户名不能为空");
            Validate.notBlank(createUserReq.getLoginName(), "登录名不能为空");
            RestUtil.printData(response, systemService.createUser(createUserReq));
        } catch (Exception e) {
            log.error("createUser error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("删除用户")
    @PostMapping("user/deleteUser")
    public void deleteUser(HttpServletRequest request, HttpServletResponse response, @RequestBody UserReq userReq) {
        try {
            Validate.notNull(userReq.getUserId(), "用户ID不能为空");
            Long userId = UserInfo.getUserId(request);
            if (userId.longValue() == userReq.getUserId().longValue()) {
                RestUtil.printData(response, CommonResult.failed("不能删除当前登录人账号"));
            }
            RestUtil.printData(response, systemService.deleteUserById(userReq.getUserId()));
        } catch (Exception e) {
            log.error("deleteUserById error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("编辑用户")
    @PostMapping("user/updateUser")
    public void updateUser(HttpServletResponse response, @RequestBody UpdateUserReq updateUserReq) {
        try {
            Validate.notNull(updateUserReq.getUserId(), "用户ID不能为空");
            RestUtil.printData(response, systemService.updateUser(updateUserReq));
        } catch (Exception e) {
            log.error("updateUser error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("用户状态禁用/启用")
    @PostMapping("user/changeUserStatus")
    public void changeUserStatus(HttpServletResponse response, @RequestBody ChangeUserStatusReq changeUserStatusReq) {
        try {
            Validate.notNull(changeUserStatusReq.getUserId(), "用户ID不能为空");
            Validate.notBlank(changeUserStatusReq.getStatus(), "状态不能为空");
            RestUtil.printData(response, systemService.changeUserStatus(changeUserStatusReq.getUserId(), changeUserStatusReq.getStatus()));
        } catch (Exception e) {
            log.error("changeUserStatus error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("用户列表")
    @GetMapping("user/selectByPage/{pageNum}/{pageSize}")
    public void selectUserByPage(HttpServletResponse response, @PathVariable("pageNum") int pageNum,
                                 @PathVariable("pageSize") int pageSize) {
        try {
            Validate.notNull(pageNum, "pageNum不能为空");
            Validate.notNull(pageSize, "pageSize不能为空");
            RestUtil.printData(response, systemService.selectUserByPage(pageNum, pageSize));
        } catch (Exception e) {
            log.error("selectUserByPage error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }


}
