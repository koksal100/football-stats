package com.football.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="game_events")
public class GameEvent {

    @Id
    @Column(name = "game_event_id")
    private String id;

    private String date;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    private int minute;
    private String type;

    @ManyToOne
    @JoinColumn(name="club_id")
    private Club club;

    @ManyToOne
    @JoinColumn(name="player_id")
    private Player player;

    private String description;

    @Column(name = "player_in_id")
    private float playerInId;

    @Column(name = "player_assist_id")
    private float playerAssistId;






}
