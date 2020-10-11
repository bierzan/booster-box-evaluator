package com.brzn.box_eval.box.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CardSetType {
    @JsonProperty("core") CORE,
    @JsonProperty("expansion") EXPANSION,
    @JsonProperty("reprint") REPRINT,
    @JsonProperty("box") BOX,
    @JsonProperty("un") UN,
    @JsonProperty("from the vault") FROM_THE_VAULT,
    @JsonProperty("premium deck") PREMIUM_DECK,
    @JsonProperty("duel deck") DUEL,
    @JsonProperty("starter") STARTER,
    @JsonProperty("commander") COMMANDER,
    @JsonProperty("planechase") PLANECHASE,
    @JsonProperty("archenemy") ARCHENEMY,
    @JsonProperty("promo") PROMO,
    @JsonProperty("vanguard") VANGUARD,
    @JsonProperty("masters") MASTERS;

}
