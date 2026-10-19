package com.football.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "clubs")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private int id;

    @Column(name = "club_code")
    private String clubCode;
    private String name;

    @ManyToOne()
    @JoinColumn(name = "domestic_competition_id")
    private Competition competition;

    @Column(name = "stadium_name")
    private String stadiumName;
    @Column(name = "coach_name")
    private String coachName;
    @Column(name = "last_season")
    private String lastSeason;


}
