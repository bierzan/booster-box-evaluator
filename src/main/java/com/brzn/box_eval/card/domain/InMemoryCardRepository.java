package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.card.dto.CardDto;
import io.vavr.collection.List;

import java.time.LocalDate;

class InMemoryCardRepository implements CardRepository {
    private List<Card> cardInventory;

    public InMemoryCardRepository() {
        cardInventory = List.empty();
    }

    @Override
    public List<Card> findCardsReleasedAfter(LocalDate date) {
        return cardInventory
                .filter(card -> card.isReleasedAfter(date))
                .toList();
    }

    @Override
    public List<Card> getAll() {
        return cardInventory;
    }

    @Override
    public void updateAll(List<CardDto> cards) {
        if (cards != null && cards.nonEmpty()) {
            cards.forEach(this::update);
        }
    }

    @Override
    public LocalDate findLastCardUpdateDate() {
        return cardInventory
                .map(Card::getLastUpdate)
                .max()
                .getOrElse(LocalDate.MIN);
    }

    private long update(CardDto cardDto) {
        return cardInventory
                .find(cardFromInventory -> cardFromInventory.refersTo(cardDto))
                .map(cardFromInventory -> cardFromInventory.updateData(cardDto)) //todo weryfikacja czy zaktualizuje
                .getOrElse(() -> {
                    cardDto.setLastUpdate(LocalDate.now());
                    return save(cardDto);
                });
    }

    public long save(CardDto cardDto) {
        Card card = Card.builder()
                .id(getNewId())
                .uuid(cardDto.getUuid())
                .name(cardDto.getName())
                .setName(cardDto.getSetName())
                .setCode(cardDto.getSetCode())
                .releasedAt(cardDto.getReleasedAt())
                .price(cardDto.getPrice())
                .lastUpdate(cardDto.getLastUpdate())
                .build();
        cardInventory = cardInventory.append(card);
        return card.getId();
    }

    private long getNewId() {
        return cardInventory
                .map(Card::getId)
                .max()
                .map(maxId -> maxId + 1L)
                .getOrElse(1L);
    }
}
