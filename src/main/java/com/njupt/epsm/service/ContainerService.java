package com.njupt.epsm.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.model.Statistics;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.njupt.epsm.common.CommonResult;
import com.njupt.epsm.common.PageDto;
import com.njupt.epsm.dto.ContainerDto;
import com.njupt.epsm.dto.TRangeDto;
import com.njupt.epsm.enumration.ContainerStatusEnum;
import com.njupt.epsm.enumration.ContainerTypeEnum;
import com.njupt.epsm.mapper.ContainerMapper;
import com.njupt.epsm.mapper.ImageMapper;
import com.njupt.epsm.po.*;
import com.njupt.epsm.req.ContainerSelectReq;
import com.njupt.epsm.util.BeanUtil;
import com.njupt.epsm.util.SeqIdUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class ContainerService {
    private static final Logger log = LoggerFactory.getLogger(ContainerService.class);

    @Autowired
    private ContainerMapper containerMapper;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private DockerClient dockerClient;


    public CommonResult createTargetContainer(Long imageId) {
        Image image = imageMapper.selectByPrimaryKey(imageId);
        if (image == null) return CommonResult.failed("镜像不存在");
        String containerId;
        try {
            //调用Docker环境 运行容器
            containerId = dockerClient.createContainerCmd(image.getImageId()).exec().getId();
            dockerClient.startContainerCmd(containerId).exec();
        } catch (Exception e) {
            log.error("createTargetContainer error", e);
            return CommonResult.success("容器创建成功");
        }
        //创建记录
        Container container = Container.builder().id(SeqIdUtil.getId())
                .containerName(ContainerTypeEnum.TARGET.getName() + "-" + System.currentTimeMillis())
                .containerType(ContainerTypeEnum.TARGET.getCode())
                .imageId(imageId)
                .containerName("靶机容器-" + System.currentTimeMillis())
                .status(ContainerStatusEnum.INITIAL.getCode())
                .containerId(containerId)
                .startTime(new Date()).build();
        containerMapper.insertSelective(container);

        return CommonResult.success("新增成功");
    }

    public CommonResult selectContainerStatus(Long id) {
        Container container = containerMapper.selectByPrimaryKey(id);
        if (container == null) return CommonResult.failed("未查询到容器");

        List<String> ids = new ArrayList<>();
        ids.add(container.getContainerId());
        com.github.dockerjava.api.model.Container container1 = dockerClient.listContainersCmd().withIdFilter(ids).exec().get(0);
        container.setStatus(container1.getStatus());
        containerMapper.updateByPrimaryKeySelective(container);
        return CommonResult.success(container1.getStatus());
    }

    public PageDto selectByPage(ContainerSelectReq selectReq) {
        Page page = PageHelper.startPage(selectReq.getPageNum(), selectReq.getPageSize());
        ContainerExample containerExample = new ContainerExample();
        ContainerExample.Criteria criteria = containerExample.createCriteria();
        if (StringUtils.isNotBlank(selectReq.getContainerName())) {
            criteria.andContainerNameLike("%".concat(selectReq.getContainerName()).concat("%"));
        }
        if (StringUtils.isNotBlank(selectReq.getStatus())) {
            criteria.andStatusEqualTo(selectReq.getStatus());
        }
        if (StringUtils.isNotBlank(selectReq.getTargetName())) {
            criteria.andRangeNameLike("%".concat(selectReq.getTargetName()).concat("%"));
        }
        containerExample.setOrderByClause("create_time desc");
        List<Container> containers = containerMapper.selectByExample(containerExample);
        List<ContainerDto> containerDtos = BeanUtil.copyBeanList(containers, ContainerDto.class);
        if (!CollectionUtils.isEmpty(containerDtos)) {
            containerDtos.stream().forEach(t -> {
                Image image = imageMapper.selectByPrimaryKey(t.getImageId());
                t.setImageName(image != null ? image.getImageName() : "");
            });

        }

        return new PageDto(selectReq.getPageNum(), selectReq.getPageSize(), page.getTotal(), containerDtos);
    }
}
