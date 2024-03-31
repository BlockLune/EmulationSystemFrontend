package com.njupt.epsm.mapper;

import com.njupt.epsm.po.LoopHole;

import java.util.List;

public interface LoopHoleMapper {

    int insert(LoopHole loopHole);

    int deleteById(Long id);

    int updateById(LoopHole loopHole);

    int countLoopHoleByName(String name);

    int countLoopHoleByCategory(Long categoryId);

    List<LoopHole> selectAll();

    List<LoopHole> selectByCategory(Long categoryId);

    List<LoopHole> selectByImage(Long imageId);

    List<LoopHole> selectByExercise(Long exerciseId);

    List<LoopHole> selectByContainer(Long containerId);

    LoopHole selectById(Long id);
}
