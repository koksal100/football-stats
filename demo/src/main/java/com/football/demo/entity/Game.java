package com.football.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    private int season;
    private String date;

    @ManyToOne
    @JoinColumn(name = "home_club_id")
    private Club homeClub;
    @ManyToOne
    @JoinColumn(name = "away_club_id")
    private Club awayClub;

    @Column(name = "home_club_goals")
    private float homeClubGoals;
    @Column(name = "away_club_goals")
    private float awayClubGoals;

    @Column(name = "home_club_manager_name")
    private String homeClubManagerName;
    @Column(name = "away_club_manager_name")
    private String awayClubManagerName;

    private String stadium;
    private String referee;
    @Column(name = "home_club_formation")
    private String homeClubFormation;
    @Column(name = "away_club_formation")
    private String awayClubFormation;

    private String aggregate;



}
