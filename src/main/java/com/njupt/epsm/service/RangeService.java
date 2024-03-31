package com.njupt.epsm.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.njupt.epsm.common.CommonResult;
import com.njupt.epsm.common.PageDto;
import com.njupt.epsm.dto.TRangeDto;
import com.njupt.epsm.mapper.TRangeMapper;
import com.njupt.epsm.po.TRange;
import com.njupt.epsm.po.TRangeExample;
import com.njupt.epsm.req.RangeSelectReq;
import com.njupt.epsm.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RangeService {

    private static final Logger log = LoggerFactory.getLogger(RangeService.class);

    @Autowired
    private TRangeMapper tRangeMapper;

    public CommonResult selectByPage(RangeSelectReq rangeSelectReq) {
        Page page = PageHelper.startPage(rangeSelectReq.getPageNum(), rangeSelectReq.getPageSize());
        TRangeExample tRangeExample = new TRangeExample();
        TRangeExample.Criteria criteria = tRangeExample.createCriteria();
        if (StringUtils.isNotBlank(rangeSelectReq.getRangeName())) {
            criteria.andRangeNameLike("%".concat(rangeSelectReq.getRangeName()).concat("%"));
        }
        if (StringUtils.isNotBlank(rangeSelectReq.getRangeStatus())) {
            criteria.andStatusEqualTo(rangeSelectReq.getRangeStatus());
        }
        tRangeExample.setOrderByClause("create_time desc");
        List<TRange> tRanges = tRangeMapper.selectByExample(tRangeExample);
        return CommonResult.success(new PageDto(rangeSelectReq.getPageNum(), rangeSelectReq.getPageSize(), page.getTotal(), BeanUtil.copyBeanList(tRanges, TRangeDto.class)));
    }

}
