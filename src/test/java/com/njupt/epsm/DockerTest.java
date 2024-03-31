package com.njupt.epsm;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.model.AuthConfig;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.LoadResponseItem;
import com.njupt.epsm.service.DockerService;
import com.njupt.epsm.service.ImageService;
import com.njupt.epsm.util.TarExtractUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@SpringBootTest(classes = EpsmApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class DockerTest {

    @Autowired
    private DockerService dockerService;
    @Autowired
    private DockerClient dockerClient;


    @Autowired
    private ImageService imageService;

    @Test
    public void listContainer() {
        dockerService.listContainer("default");
    }


    @Test
    public void uploadImage() throws Exception {
        try {
//            TarExtractUtil.extractTarFile("/Users/liangwei/Downloads/dokcerfile/nginx/nginx.tar", "/Users/liangwei/Downloads/dokcerfile/nginx");
//            File file = new File("/Users/liangwei/Downloads/dokcerfile/nginx/Dockerfile");
//            BuildImageResultCallback start = dockerClient.buildImageCmd(file).start();
//            String s = start.awaitImageId();
            dockerClient.tagImageCmd("fa254f61b1ed", "nginx", "v1").exec();
//            System.out.println(s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        File imageFile = new File("/Users/liangwei/Downloads/redis1.tar");
//        imageFile.createNewFile();
//        imageFile.deleteOnExit();
//        final byte[] buffer = new byte[2048];
//        int read;
//
//        try (OutputStream imageOutput = new BufferedOutputStream(new FileOutputStream(imageFile))) {
//            try (InputStream imageInput = dockerClient.save("redis:lastest")) {
//                while ((read = imageInput.read(buffer)) > -1) {
//                    imageOutput.write(buffer, 0, read);
//                }
//            }
//        }
    }
}
