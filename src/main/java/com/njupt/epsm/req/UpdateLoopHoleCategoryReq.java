package com.njupt.epsm.req;

import lombok.Data;

@Data
public class UpdateLoopHoleCategoryReq {

    private Long id;

    private String categoryName;

    private String descr;
}
