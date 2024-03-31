package com.njupt.epsm.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

@Data
public class ImageIdReq implements Serializable {

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;
}
