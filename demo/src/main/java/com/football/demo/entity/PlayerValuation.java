package com.football.demo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="player_valuations")
public class PlayerValuation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PostgreSQL için genellikle IDENTITY kullanılır
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    private String date;
    @Column(name = "market_value_in_eur")
    private int marketValueInEur;

}
