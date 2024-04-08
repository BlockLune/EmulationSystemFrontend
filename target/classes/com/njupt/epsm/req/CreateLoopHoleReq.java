package com.njupt.epsm.req;

import lombok.Data;

@Data
public class CreateLoopHoleReq {

    private String holeName;

    private double level;

    private Long categoryId;

    private String metadata;

    private Long relateExerciseId;

    private Long relateImageId;

    private Long relateContainerId;
}
