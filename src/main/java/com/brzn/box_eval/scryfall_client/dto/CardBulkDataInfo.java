package com.brzn.box_eval.scryfall_client.dto;

import lombok.Getter;
import lombok.Value;

import java.net.URI;
import java.time.LocalDateTime;

@Value
public class CardBulkDataInfo {
    LocalDateTime updatedAt;
    @Getter
    URI downloadUri;
}
