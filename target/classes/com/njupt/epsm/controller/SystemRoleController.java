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
@Api(tags = "角色管理")
public class SystemRoleController {

    @Autowired
    private SystemService systemService;

    private static final Logger log = LoggerFactory.getLogger(SystemRoleController.class);

    @ApiModelProperty("创建角色")
    @PostMapping("role/createRole")
    public void createRole(HttpServletResponse response, @RequestBody CreateRoleReq createRoleReq) {
        try {
            Validate.notBlank(createRoleReq.getRoleName(), "角色名称不能为空");
            Validate.notBlank(createRoleReq.getAuth(), "权限不能为空");
            RestUtil.printData(response, systemService.createRole(createRoleReq));
        } catch (Exception e) {
            log.error("createRole error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("删除角色")
    @PostMapping("role/deleteRole")
    public void deleteRole(HttpServletRequest request, HttpServletResponse response, @RequestBody RoleReq roleReq) {
        try {
            Validate.notNull(roleReq.getRoleId(), "角色ID不能为空");
            Long roleId = UserInfo.getRoleId(request);
            if (roleId.longValue() == roleReq.getRoleId().longValue()) {
                RestUtil.printData(response, CommonResult.failed("不能删除当前登录人角色"));
            }
            RestUtil.printData(response, systemService.deleteRoleById(roleReq.getRoleId()));
        } catch (Exception e) {
            log.error("deleteRole error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("编辑角色")
    @PostMapping("role/updateRole")
    public void updateRole(HttpServletResponse response, @RequestBody UpdateRoleReq updateRoleReq) {
        try {
            Validate.notNull(updateRoleReq.getRoleId(), "角色ID不能为空");
            RestUtil.printData(response, systemService.updateRole(updateRoleReq));
        } catch (Exception e) {
            log.error("updateRole error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("角色列表")
    @GetMapping("role/selectByPage/{pageNum}/{pageSize}")
    public void selectByPage(HttpServletResponse response, @PathVariable("pageNum") int pageNum,
                             @PathVariable("pageSize") int pageSize) {
        try {
            Validate.notNull(pageNum, "pageNum不能为空");
            Validate.notNull(pageSize, "pageSize不能为空");
            RestUtil.printData(response, systemService.selectRoleByPage(pageNum, pageSize));
        } catch (Exception e) {
            log.error("selectByPage error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("查询所有角色")
    @GetMapping("role/listRoles")
    public void listRoles(HttpServletResponse response) {
        try {
            RestUtil.printData(response, systemService.listRoles());
        } catch (Exception e) {
            log.error("listRoles error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }



}
