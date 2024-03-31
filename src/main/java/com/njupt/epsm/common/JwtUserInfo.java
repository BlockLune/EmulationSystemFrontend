package com.njupt.epsm.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liangw
 * @create 2021-09-18 12:26
 */
@Data
@AllArgsConstructor
public class JwtUserInfo {

    private String userId;

    private String userName;


    private Long roleId;


}
