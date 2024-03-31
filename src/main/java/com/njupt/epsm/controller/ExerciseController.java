package com.njupt.epsm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("exercise")
@Api(tags = "仿真靶场——攻防演练")
public class ExerciseController {

    private static final Logger log = LoggerFactory.getLogger(ExerciseController.class);

    @PostMapping("createExercise")
    @ApiOperation("新增演练")
    public void createExercise() {

    }

    @PostMapping("selectTarget")
    @ApiOperation("查询演练靶机")
    public void selectTarget(HttpServletResponse response) {

    }

}
