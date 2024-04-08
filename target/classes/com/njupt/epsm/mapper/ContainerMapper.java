package com.njupt.epsm.mapper;

import com.njupt.epsm.po.Container;
import com.njupt.epsm.po.ContainerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContainerMapper {
    long countByExample(ContainerExample example);

    int deleteByExample(ContainerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Container record);

    int insertSelective(Container record);

    List<Container> selectByExample(ContainerExample example);

    Container selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Container record, @Param("example") ContainerExample example);

    int updateByExample(@Param("record") Container record, @Param("example") ContainerExample example);

    int updateByPrimaryKeySelective(Container record);

    int updateByPrimaryKey(Container record);
}