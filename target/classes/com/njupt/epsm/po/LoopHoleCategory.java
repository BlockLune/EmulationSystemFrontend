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
public class LoopHoleCategory implements Serializable {

    private Long id;

    private String categoryName;

    private String descr;

    private Date createTime;

    private Date updateTime;
}
