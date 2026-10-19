package com.football.demo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "game_lineups")
public class GameLineup {

    @Id
    @Column(name = "game_lineups_id")
    private String id;
    private String date;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name="club_id")
    private Club club;

    private String type;
    private String position;
    private String number;

    @Column(name = "team_captain")
    private int teamCaptain;
}
