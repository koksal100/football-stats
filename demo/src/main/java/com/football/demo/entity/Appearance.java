package com.football.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "appearances")
public class Appearance {

    @Id
    @Column(name = "appearance_id")
    private String id;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name="player_id")
    private Player player;

    @Column(name="player_club_id")
    private int playerClubId;

    private String date;

    @Column(name="player_name")
    private String playerName;

    @ManyToOne
    @JoinColumn(name="competition_id")
    private Competition competition;

    @Column(name="yellow_cards")
    private int yellowCards;

    @Column(name="red_cards")
    private int redCards;

    private int goals;
    private int assists;
    @Column(name = "minutes_played")
    private int minutesPlayed;
}
