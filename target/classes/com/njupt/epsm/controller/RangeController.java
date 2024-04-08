package com.njupt.epsm.controller;


import com.njupt.epsm.common.CommonResult;
import com.njupt.epsm.req.RangeSelectReq;
import com.njupt.epsm.service.RangeService;
import com.njupt.epsm.util.RestUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("range")
@Api(tags = "仿真靶场——靶场管理")
public class RangeController {

    private static final Logger log = LoggerFactory.getLogger(RangeController.class);

    @Autowired
    private RangeService rangeService;


    @PostMapping("selectByPage")
    public void selectByPage(HttpServletResponse response, @RequestBody RangeSelectReq rangeSelectReq) {
        try {
            RestUtil.printData(response, rangeService.selectByPage(rangeSelectReq));
        } catch (Exception e) {
            log.error("selectByPage error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }
}
