package com.njupt.epsm.common;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class PageDto implements Serializable {

    private int pageNum;

    private int pageSize;

    private long total;

    private Object list;


    public <T> List<T> getList(Class<T> responseType) {
        return JSONObject.parseArray(JSONObject.toJSONString(this.getList())).toJavaList(responseType);

    }

    public static PageDto empty(int pageNum,int pageSize) {
        return new PageDto(pageNum, pageSize, 0, null);

    }
}
