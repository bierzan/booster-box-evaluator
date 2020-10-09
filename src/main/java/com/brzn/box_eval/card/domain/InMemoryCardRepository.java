package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.card.domain.dto.CardDto;
import io.vavr.collection.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

class InMemoryCardRepository implements CardRepository {
    private LocalDateTime updateDate;
    private List<Card> cardInventory;

    public InMemoryCardRepository() {
        updateDate = LocalDateTime.MIN;
        cardInventory = List.empty();
    }

    @Override
    public List<Card> findCardsReleasedAfter(LocalDate date){
        return cardInventory
                .filter(card -> card.isReleasedAfter(date))
                .toList();
    }

    @Override
    public List<Card> getAll(){
        return cardInventory;
    }

    @Override
    public void updateAll(List<CardDto> cards){
        cards.forEach(this::update);
        updateDate = LocalDateTime.now();
    }

    private long update(CardDto cardDto) {
        return cardInventory
                .find(cardFromInventory -> cardFromInventory.refersTo(cardDto))
                .map(cardFromInventory -> cardFromInventory.updateData(cardDto)) //todo weryfikacja czy zaktualizuje
                .getOrElse(()->save(cardDto));
    }

    private long save(CardDto cardDto) {
        Card card = Card.builder()
                .id(getNewId())
                .name(cardDto.getName())
                .setName(cardDto.getSetName())
                .setCode(cardDto.getSetCode())
                .releasedAt(cardDto.getReleasedAt())
                .price(cardDto.getPrice())
                .build();
        cardInventory = cardInventory.append(card);
        return card.getId();
    }

    private long getNewId() {
        return cardInventory
                .map(Card::getId)
                .max()
                .map(maxId -> maxId+1L)
                .getOrElse(1L);
    }

    @Override
    public boolean isOlderThan(LocalDateTime updatedAt) { //todo test
        return updateDate.isBefore(updatedAt);
    }
}
