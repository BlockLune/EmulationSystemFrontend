package com.njupt.epsm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njupt.epsm.common.CommonResult;
import com.njupt.epsm.mapper.LoopHoleCategoryMapper;
import com.njupt.epsm.mapper.LoopHoleMapper;
import com.njupt.epsm.po.LoopHole;
import com.njupt.epsm.po.LoopHoleCategory;
import com.njupt.epsm.req.CreateLoopHoleCategoryReq;
import com.njupt.epsm.req.CreateLoopHoleReq;
import com.njupt.epsm.req.UpdateLoopHoleCategoryReq;
import com.njupt.epsm.req.UpdateLoopHoleReq;
import com.njupt.epsm.util.SeqIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LoopHoleService {

    @Autowired
    private LoopHoleCategoryMapper loopHoleCategoryMapper;

    @Autowired
    private LoopHoleMapper loopHoleMapper;

    public CommonResult createLoopHoleCategory(CreateLoopHoleCategoryReq createLoopHoleCategoryReq){
        int count = loopHoleCategoryMapper.countByName(createLoopHoleCategoryReq.getCategoryName());
        if(count > 0){
            return CommonResult.failed("漏洞分类已存在");
        }

        LoopHoleCategory loopHoleCategory = new LoopHoleCategory();
        loopHoleCategory.setCategoryName(createLoopHoleCategoryReq.getCategoryName());
        loopHoleCategory.setDescr(createLoopHoleCategoryReq.getDescr());
        loopHoleCategory.setId(SeqIdUtil.getId());
        loopHoleCategory.setCreateTime(new Date());
        loopHoleCategory.setUpdateTime(new Date());
        loopHoleCategoryMapper.insert(loopHoleCategory);
        return CommonResult.success();
    }

    public CommonResult deleteLoopHoleCategoryById(Long id){
        int num = loopHoleCategoryMapper.deleteById(id);
        return num > 0 ? CommonResult.success("删除成功") : CommonResult.failed("删除失败");
    }

    public CommonResult updateLoopHoleCategoryById(UpdateLoopHoleCategoryReq updateLoopHoleCategoryReq){
        LoopHoleCategory loopHoleCategory = new LoopHoleCategory();
        loopHoleCategory.setId(updateLoopHoleCategoryReq.getId());
        loopHoleCategory.setCategoryName(updateLoopHoleCategoryReq.getCategoryName());
        loopHoleCategory.setDescr(updateLoopHoleCategoryReq.getDescr());
        loopHoleCategory.setUpdateTime(new Date());
        int num = loopHoleCategoryMapper.updateById(loopHoleCategory);
        return num > 0 ? CommonResult.success("更新成功") : CommonResult.failed("更新失败");
    }

    public CommonResult getAllLoopHoleCategory(int page,int size){
        PageHelper.startPage(page,size);
        List<LoopHoleCategory> loopHoleCategories = loopHoleCategoryMapper.selectAll();
        PageInfo<LoopHoleCategory> loopHoleCategoryPageInfo = new PageInfo<>(loopHoleCategories);
        return CommonResult.success(loopHoleCategoryPageInfo);
    }

    public CommonResult getLoopHoleCategoryById(Long id){
        LoopHoleCategory loopHoleCategory = loopHoleCategoryMapper.selectById(id);
        return CommonResult.success(loopHoleCategory);
    }

    public CommonResult createLoopHole(CreateLoopHoleReq createLoopHoleReq){
        int count = loopHoleMapper.countLoopHoleByName(createLoopHoleReq.getHoleName());
        if(count > 0){
            return CommonResult.failed("漏洞名已存在");
        }

        LoopHole loopHole = new LoopHole();
        loopHole.setId(SeqIdUtil.getId());
        loopHole.setHoleName(createLoopHoleReq.getHoleName());
        loopHole.setCategoryId(createLoopHoleReq.getCategoryId());
        loopHole.setLevel(createLoopHoleReq.getLevel());
        loopHole.setMetadata(createLoopHoleReq.getMetadata());
        loopHole.setRelateExerciseId(createLoopHoleReq.getRelateExerciseId());
        loopHole.setRelateImageId(createLoopHoleReq.getRelateImageId());
        loopHole.setRelateContainerId(createLoopHoleReq.getRelateContainerId());
        loopHole.setCreateTime(new Date());
        loopHole.setUpdateTime(new Date());

        loopHoleMapper.insert(loopHole);
        return CommonResult.success();
    }

    public CommonResult deleteLoopHoleById(Long id){
        int num = loopHoleMapper.deleteById(id);
        return num > 0 ? CommonResult.success("删除成功") : CommonResult.failed("删除失败");
    }

    public CommonResult updateLoopHoleById(UpdateLoopHoleReq updateLoopHoleReq){
        LoopHole loopHole = new LoopHole();
        loopHole.setId(updateLoopHoleReq.getId());
        loopHole.setHoleName(updateLoopHoleReq.getHoleName());
        loopHole.setMetadata(updateLoopHoleReq.getMetadata());
        loopHole.setLevel(updateLoopHoleReq.getLevel());
        loopHole.setCategoryId(updateLoopHoleReq.getCategoryId());
        loopHole.setRelateExerciseId(updateLoopHoleReq.getRelateExerciseId());
        loopHole.setRelateImageId(updateLoopHoleReq.getRelateImageId());
        loopHole.setRelateContainerId(updateLoopHoleReq.getRelateContainerId());
        loopHole.setUpdateTime(new Date());

        int num = loopHoleMapper.updateById(loopHole);
        return num > 0 ? CommonResult.success("更新成功") : CommonResult.failed("更新失败");
    }

    public CommonResult countLoopHoleByCategory(Long categoryId){
        return CommonResult.success(loopHoleMapper.countLoopHoleByCategory(categoryId));
    }

    public CommonResult getAllLoopHole(int page,int size){
        PageHelper.startPage(page, size);
        List<LoopHole> loopHoles = loopHoleMapper.selectAll();
        PageInfo<LoopHole> loopHolePageInfo = new PageInfo<>(loopHoles);
        return CommonResult.success(loopHolePageInfo);
    }

    public CommonResult getLoopHoleByCategory(Long categoryId, int page,int size){
        PageHelper.startPage(page, size);
        List<LoopHole> loopHoles = loopHoleMapper.selectByCategory(categoryId);
        PageInfo<LoopHole> loopHolePageInfo = new PageInfo<>(loopHoles);
        return CommonResult.success(loopHolePageInfo);
    }

    public CommonResult getLoopHoleByExercise(Long exerciseId, int page,int size){
        PageHelper.startPage(page, size);
        List<LoopHole> loopHoles = loopHoleMapper.selectByExercise(exerciseId);
        PageInfo<LoopHole> loopHolePageInfo = new PageInfo<>(loopHoles);
        return CommonResult.success(loopHolePageInfo);
    }

    public CommonResult getLoopHoleByImage(Long imageId, int page,int size){
        PageHelper.startPage(page, size);
        List<LoopHole> loopHoles = loopHoleMapper.selectByImage(imageId);
        PageInfo<LoopHole> loopHolePageInfo = new PageInfo<>(loopHoles);
        return CommonResult.success(loopHolePageInfo);
    }

    public CommonResult getLoopHoleByContainer(Long containerId, int page,int size){
        PageHelper.startPage(page, size);
        List<LoopHole> loopHoles = loopHoleMapper.selectByContainer(containerId);
        PageInfo<LoopHole> loopHolePageInfo = new PageInfo<>(loopHoles);
        return CommonResult.success(loopHolePageInfo);
    }

    public CommonResult getLoopHoleById(Long id){
        LoopHole loopHole = loopHoleMapper.selectById(id);
        return CommonResult.success(loopHole);
    }
}
