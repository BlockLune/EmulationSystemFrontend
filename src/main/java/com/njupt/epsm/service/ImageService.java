package com.njupt.epsm.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.njupt.epsm.common.CommonResult;
import com.njupt.epsm.common.PageDto;
import com.njupt.epsm.dto.ImageDto;
import com.njupt.epsm.enumration.ImageTypeEnum;
import com.njupt.epsm.mapper.ImageMapper;
import com.njupt.epsm.po.Image;
import com.njupt.epsm.po.ImageExample;
import com.njupt.epsm.req.ImageSelectReq;
import com.njupt.epsm.util.BeanUtil;
import com.njupt.epsm.util.SeqIdUtil;
import com.njupt.epsm.util.TarExtractUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    private static final Logger log = LoggerFactory.getLogger(ImageService.class);

    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private DockerClient dockerClient;

    public CommonResult selectByPage(ImageSelectReq selectReq) {
        Page page = PageHelper.startPage(selectReq.getPageNum(), selectReq.getPageSize());
        ImageExample imageExample = new ImageExample();
        ImageExample.Criteria criteria = imageExample.createCriteria();
        if (StringUtils.isNotBlank(selectReq.getImageName())) {
            criteria.andImageNameLike("%".concat(selectReq.getImageName()).concat("%"));
        }
        if (StringUtils.isNotBlank(selectReq.getImageType())) {
            criteria.andImageTypeEqualTo(selectReq.getImageType());
        }
        List<Image> images = imageMapper.selectByExample(imageExample);
        return CommonResult.success(new PageDto(selectReq.getPageNum(), selectReq.getPageSize(), page.getTotal(), BeanUtil.copyBeanList(images, ImageDto.class)));
    }

    public CommonResult uploadImage(String imageName, String version, String imageType, MultipartFile file) {
        String path = "/tmp/" + UUID.randomUUID().toString().replace("-", "").substring(0, 8) + file.getOriginalFilename();
        String imageId;
        try {
            file.transferTo(new File(path));
            TarExtractUtil.extractTarFile(path, "/tmp/" + imageName);
            File dockerFile = new File("/tmp/" + imageName + "/Dockerfile");
            BuildImageResultCallback start = dockerClient.buildImageCmd(dockerFile).start();
            imageId = start.awaitImageId();
            dockerClient.tagImageCmd(imageId, imageName, version).exec();
        } catch (Exception e) {
            log.error("uploadImage error", e);
            return CommonResult.failed("文件上传失败");
        }
        Image image = new Image();
        image.setId(SeqIdUtil.getId());
        image.setImageName(imageName);
        image.setImageType(imageType);
        image.setImageId(imageId);
        image.setPath(path);
        image.setVersion(version);
        imageMapper.insertSelective(image);
        return CommonResult.success("上传成功");
    }

    public CommonResult deleteImage(Long imageId) {
        imageMapper.deleteByPrimaryKey(imageId);
        return CommonResult.success("删除成功");
    }

    public List<ImageDto> selectTargetImage() {
        ImageExample imageExample = new ImageExample();
        imageExample.createCriteria().andImageTypeEqualTo(ImageTypeEnum.TARGET.getCode());
        imageExample.setOrderByClause("create_time desc");
        List<Image> images = imageMapper.selectByExample(imageExample);
        return BeanUtil.copyBeanList(images, ImageDto.class);
    }
}
