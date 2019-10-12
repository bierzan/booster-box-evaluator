package com.brzn.bboxeval.box;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "booster_box")
@Getter
@Setter
@NoArgsConstructor
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cardSetCode;
    private LocalDate releaseDate;
    private String type;
    private String booster;
    private short boosterQuantity;
}
