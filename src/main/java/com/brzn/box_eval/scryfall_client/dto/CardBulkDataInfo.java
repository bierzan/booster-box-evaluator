package com.brzn.box_eval.scryfall_client.dto;

import lombok.Getter;
import lombok.Value;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
public class CardBulkDataInfo {
    LocalDateTime updatedAt;
    @Getter
    URL downloadUrl;

    public boolean wasUpDatedAfter(LocalDate date) {
        return updatedAt.isAfter(date.atStartOfDay());
    }
}
