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
public class QemuTask implements Serializable {

    private Long id;

    private Long pluginId;

    private String containerId;

    private String taskStatus;

    private Date createTime;

    private Date updateTime;

    public class TaskStatus{
        public static final String CREATED = "已创建";

        public static final String EXECUTING = "正在运行";

        public static final String FINISHED = "已结束";

        public static final String DESTROYED = "已结束并销毁";
    }
}
