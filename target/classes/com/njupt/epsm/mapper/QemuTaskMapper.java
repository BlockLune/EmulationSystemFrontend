package com.njupt.epsm.mapper;

import com.njupt.epsm.po.QemuTask;

import java.util.List;

public interface QemuTaskMapper {

    public int insert(QemuTask qemuTask);

    public int deleteById(Long id);

    public List<QemuTask> selectAll();

    public QemuTask selectById(Long id);

    public List<QemuTask> selectByPluginId(Long pluginId);

    public int updateStatusByContainerId(QemuTask qemuTask);


}
