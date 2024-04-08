package com.njupt.epsm.controller;

import com.njupt.epsm.common.CommonResult;
import com.njupt.epsm.req.CreateConfigReq;
import com.njupt.epsm.req.DeleteConfigReq;
import com.njupt.epsm.req.UpdateConfigReq;
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
@Api(tags = "配置管理")
public class SystemConfigController {

    private static final Logger log = LoggerFactory.getLogger(SystemConfigController.class);

    @Autowired
    private SystemService systemService;


    @ApiModelProperty("配置管理/新增")
    @PostMapping("config/createConfig")
    public void createConfig(HttpServletResponse response, HttpServletRequest request, @RequestBody CreateConfigReq createConfigReq) {
        try {
            Validate.notBlank(createConfigReq.getConfigItem(), "配置项参数不能为空");
            Validate.notBlank(createConfigReq.getConfigValue(), "配置值不能为空");
            RestUtil.printData(response, systemService.createConfig(createConfigReq));
        } catch (Exception e) {
            log.error("createConfig error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("配置管理/编辑")
    @PostMapping("config/updateConfig")
    public void updateConfig(HttpServletResponse response, HttpServletRequest request, @RequestBody UpdateConfigReq updateConfigReq) {
        try {
            Validate.notNull(updateConfigReq.getConfigId(), "配置Id不能为空");
            Validate.notBlank(updateConfigReq.getConfigItem(), "配置项参数不能为空");
            Validate.notBlank(updateConfigReq.getConfigValue(), "配置值不能为空");
            RestUtil.printData(response, systemService.updateConfig(updateConfigReq));
        } catch (Exception e) {
            log.error("updateConfig error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("配置管理/删除")
    @PostMapping("config/deleteConfig")
    public void deleteConfig(HttpServletResponse response, HttpServletRequest request, @RequestBody DeleteConfigReq deleteConfigReq) {
        try {
            Validate.notNull(deleteConfigReq.getConfigId(), "配置Id不能为空");
            RestUtil.printData(response, systemService.deleteConfig(deleteConfigReq.getConfigId()));
        } catch (Exception e) {
            log.error("deleteConfig error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("配置列表")
    @GetMapping("config/selectByPage/{pageNum}/{pageSize}")
    public void selectConfigByPage(HttpServletResponse response, @PathVariable("pageNum") int pageNum,
                                   @PathVariable("pageSize") int pageSize) {
        try {
            Validate.notNull(pageNum, "pageNum不能为空");
            Validate.notNull(pageSize, "pageSize不能为空");
            RestUtil.printData(response, systemService.selectConfigByPage(pageNum, pageSize));
        } catch (Exception e) {
            log.error("selectConfigByPage error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }
}
