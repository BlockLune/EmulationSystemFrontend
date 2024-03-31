package com.njupt.epsm.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateQemuPluginReq {

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("挖掘插件名称")
    private String qemuPluginName;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("插件版本")
    private String version;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ApiModelProperty("插件镜像")
    private MultipartFile file;

}
