package com.njupt.epsm.mapper;

import com.njupt.epsm.po.QemuPlugin;

import java.util.List;

public interface QemuPluginMapper {

    int insert(QemuPlugin qemuPlugin);

    int deleteById(Long id);

    List<QemuPlugin> selectAll();

    QemuPlugin selectById(Long id);

    List<QemuPlugin> selectByName(String name);
}
