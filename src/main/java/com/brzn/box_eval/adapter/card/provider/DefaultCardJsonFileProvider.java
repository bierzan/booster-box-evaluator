package com.brzn.box_eval.adapter.card.provider;

import com.brzn.box_eval.card.port.CardJsonFileProvider;
import com.brzn.box_eval.infrastructure.client.Client;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

@Slf4j
@AllArgsConstructor
public class DefaultCardJsonFileProvider implements CardJsonFileProvider {
    public static final String FILE_PATH = "cards.json";
    private final Client client;

    @Override
    public File getCardsJsonFileReleasedAfter(LocalDate date) {
        return Option.of(client.getUrlForCardDateUpdatedAfter(date))
                .map(this::saveUrlToJsonFile)
                .getOrNull();
    }

    private File saveUrlToJsonFile(URL url) {
        try {
            log.info(String.format("Downloading file from url %s", url.toString()));
            FileUtils.copyURLToFile(url, new File(FILE_PATH));
            return FileUtils.getFile(FILE_PATH);
        } catch (IOException e) {
            log.info(String.format("Downloading file from url %s failed. card.json file not saved", url.toString()));
            e.printStackTrace();
        }
        return null;
    }
}
