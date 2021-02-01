package com.brzn.box_eval.adapter.card.provider;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Slf4j
class FileDownloader { //todo wyniesc do innego pakietu
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
