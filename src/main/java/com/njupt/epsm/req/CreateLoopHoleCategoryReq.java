package com.njupt.epsm.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateLoopHoleCategoryReq implements Serializable {

    private String categoryName;

    private String descr;

}
