package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.card.dto.CardDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;
    private String uuid;
    private String name;
    private LocalDate releasedAt;
    private String setName;
    private String setCode;
    private BigDecimal price;
    @Getter
    private LocalDate lastUpdate;

    public boolean isReleasedAfter(LocalDate date) {
        return releasedAt.isAfter(date);
    }

    public long updateData(CardDto cardDto) {
        price = cardDto.getPrice();
        lastUpdate = LocalDate.now();
        return id;
    }

    public boolean refersTo(CardDto dto) {
        return uuid.equals(dto.getUuid());
    }

    public CardDto dto() {
        return CardDto.builder()
                .uuid(uuid)
                .name(name)
                .releasedAt(releasedAt)
                .lastUpdate(lastUpdate)
                .setName(setName)
                .setCode(setCode)
                .price(price)
                .build();
    }
}
