package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.card.domain.dto.CardDto;
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
    private long uuid;
    private String name;
    private LocalDate releasedAt;
    private String setName;
    private String setCode;
    private BigDecimal price;

    public boolean isReleasedAfter(LocalDate date) {
        return releasedAt.isAfter(date);
    }

    public long updateData(CardDto cardDto) {
        price = cardDto.getPrice();
        return id;
    }

    public boolean refersTo(CardDto dto) {
        return uuid ==  dto.getUuid();
    }

    public CardDto dto() {
        return CardDto.builder()
                .uuid(uuid)
                .name(name)
                .releasedAt(releasedAt)
                .setName(setName)
                .setCode(setCode)
                .price(price)
                .build();
    }
}
