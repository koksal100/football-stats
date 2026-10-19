package com.football.demo.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String name;
    @Column(name = "last_season")
    private int lastSeason;
    @ManyToOne
    @JoinColumn(name = "current_club_id")
    private Club currentClub;
    @Column(name = "country_of_birts")
    private String countryOfBirth;
    @Column(name = "city_of_birth")
    private String cityOfBirth;
    @Column(name = "country_of_citizenship")
    private String countryOfCitizenship;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Column(name = "sub_position")
    private String subPosition;
    private String position;
    private String foot;
    @Column(name = "height_in_cm")
    private float heightInCm;
    @Column(name = "contract_expiration_date")
    private String contractExpirationDate;
    @Column(name = "agent_name")
    private String agentName;
    @Column(name = "market_value_in_eur")
    private float marketValueInEur;
    @Column(name = "highest_market_value_in_eur")
    private float highestMarketValueInEur;



}