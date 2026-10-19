package com.football.demo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "transfers")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PostgreSQL için genellikle IDENTITY kullanılır
    private Long id;

    @ManyToOne
    @JoinColumn(name="player_id")
    private Player player;
    @Column(name = "transfer_date")
    private String transferDate;

    @ManyToOne
    @JoinColumn(name = "from_club_id")
    private Club fromClub;
    @ManyToOne
    @JoinColumn(name="to_club_id")
    private Club toClub;

    private Float transferFee;

}
