package com.brzn.box_eval.application;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Slf4j
@Component
public class FileDownloader {
    public File getFileFromUrl(URL url, String path) {
        try {
            log.info("Downloading file from url {}", url.toString());
            FileUtils.copyURLToFile(url, new File(path));
            return FileUtils.getFile(path);
        } catch (IOException e) {
            log.info("Downloading file from url {} failed. card.json file not saved", url.toString());
            e.printStackTrace();
        }
        return null;
    }
}
