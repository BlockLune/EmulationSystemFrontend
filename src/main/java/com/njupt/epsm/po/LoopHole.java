package com.njupt.epsm.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoopHole implements Serializable {

    private Long id;

    private String holeName;

    private double level;

    private Long categoryId;

    private String metadata;

    private Long relateExerciseId;

    private Long relateImageId;

    private Long relateContainerId;

    private Date createTime;

    private Date updateTime;
}
