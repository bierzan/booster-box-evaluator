package com.brzn.box_eval.adapter.card.provider;

import com.brzn.box_eval.card.port.CardJsonFileProvider;
import com.brzn.box_eval.infrastructure.client.Client;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
public class DefaultCardJsonFileProvider implements CardJsonFileProvider {
    private static final String FILE_PATH = "cards.json";
    private final FileDownloader downloader;
    private final Client client;

    @Override
    public File getCardsJsonFileReleasedAfter(LocalDate date) {
        return Option.of(client.getUrlForCardDateUpdatedAfter(date))
                .map(url -> downloader.getFileFromUrl(url, FILE_PATH))
                .getOrNull();
    }
}
