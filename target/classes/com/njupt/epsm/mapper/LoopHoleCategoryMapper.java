package com.njupt.epsm.mapper;

import com.njupt.epsm.po.LoopHoleCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoopHoleCategoryMapper {

    int insert(LoopHoleCategory loopHoleCategory);

    int deleteById(@Param("id") Long id);

    int countByName(String name);

    int updateById(LoopHoleCategory loopHoleCategory);

    List<LoopHoleCategory> selectAll();

    LoopHoleCategory selectById(Long id);
}
