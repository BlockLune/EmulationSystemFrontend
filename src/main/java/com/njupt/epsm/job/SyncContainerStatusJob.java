package com.njupt.epsm.job;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.njupt.epsm.mapper.ContainerMapper;
import com.njupt.epsm.po.ContainerExample;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Component
public class SyncContainerStatusJob {

    private static final Logger logger = LoggerFactory.getLogger(SyncContainerStatusJob.class);

    @Autowired
    private DockerClient dockerClient;

    @Autowired
    private ContainerMapper containerMapper;


    @Scheduled(cron = "0/20 * * * * ?")
    public void syncContainerStatus() {
        List<Container> containers = dockerClient.listContainersCmd().exec();
        if (!CollectionUtils.isEmpty(containers)) {
            for (Container container : containers) {
                try {
                    ContainerExample containerExample = new ContainerExample();
                    containerExample.createCriteria().andContainerIdEqualTo(container.getId());
                    List<com.njupt.epsm.po.Container> containers1 = containerMapper.selectByExample(containerExample);
                    if (!CollectionUtils.isEmpty(containers1)) {
                        com.njupt.epsm.po.Container container1 = containers1.get(0);
                        container1.setStatus(container.getStatus());
                        if (StringUtils.equals(container1.getStatus(), "exited")) {
                            container1.setEndTime(new Date());
                        }
                        containerMapper.updateByPrimaryKeySelective(container1);
                    }
                } catch (Exception e) {
                    logger.error("syncContainerStatus error,containerId:{}", container.getId(), e);
                }
            }
        }
    }
}
