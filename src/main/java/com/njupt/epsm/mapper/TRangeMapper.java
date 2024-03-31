package com.njupt.epsm.mapper;

import com.njupt.epsm.po.TRange;
import com.njupt.epsm.po.TRangeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRangeMapper {
    long countByExample(TRangeExample example);

    int deleteByExample(TRangeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TRange record);

    int insertSelective(TRange record);

    List<TRange> selectByExample(TRangeExample example);

    TRange selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TRange record, @Param("example") TRangeExample example);

    int updateByExample(@Param("record") TRange record, @Param("example") TRangeExample example);

    int updateByPrimaryKeySelective(TRange record);

    int updateByPrimaryKey(TRange record);
}