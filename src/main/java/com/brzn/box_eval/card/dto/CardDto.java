package com.brzn.box_eval.card.dto;

import com.brzn.box_eval.card.PriceDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDto {
    @JsonIgnore
    private long id;
    @JsonProperty(value = "id")
    private String uuid;
    private String name;
    @JsonProperty(value = "released_at")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate releasedAt;
    private LocalDate lastUpdate;
    @JsonProperty(value = "set_name")
    private String setName;
    @JsonProperty(value = "set")
    private String setCode;
    @JsonProperty(value = "prices")
    @JsonDeserialize(using = PriceDeserializer.class)
    private BigDecimal price;

    public boolean isValid() {
        return exists(uuid) &&
                exists(name) &&
                releasedAt != null &&
                exists(setName) &&
                exists(setCode) &&
                price != null;
    }

    private boolean exists(String property) {
        return !StringUtils.isEmpty(property);
    }
}
