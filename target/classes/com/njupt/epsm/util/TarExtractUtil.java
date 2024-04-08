package com.njupt.epsm.util;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TarExtractUtil {

    public static void extractTarFile(String tarFilePath, String outputDirPath) throws IOException {
        File tarFile = new File(tarFilePath);
        File outputDir = new File(outputDirPath);

        try (TarArchiveInputStream tarIn = new TarArchiveInputStream(new FileInputStream(tarFile))) {
            TarArchiveEntry entry;

            // 循环遍历tar文件中的内容
            while ((entry = (TarArchiveEntry) tarIn.getNextEntry()) != null) {
                // 对于每个条目，创建对应的文件/目录
                File outputFile = new File(outputDir, entry.getName());

                if (entry.isDirectory()) {
                    if (!outputFile.exists()) {
                        if (!outputFile.mkdirs()) {
                            throw new IOException("Failed to create directory " + outputFile.getAbsolutePath());
                        }
                    }
                } else {
                    // 确保父目录存在
                    File parent = outputFile.getParentFile();
                    if (!parent.exists()) {
                        if (!parent.mkdirs()) {
                            throw new IOException("Failed to create directory " + parent.getAbsolutePath());
                        }
                    }

                    try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile))) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = tarIn.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, length);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            String tarFilePath = "/Users/liangwei/Downloads/dokcerfile/nginx/nginx.tar";
            String outputDirPath = "/Users/liangwei/Downloads/dokcerfile/nginx";
            extractTarFile(tarFilePath, outputDirPath);
            System.out.println("Extraction complete.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
