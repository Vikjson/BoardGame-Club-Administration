package se.yrgo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int gameId;

    private String gameName;
    private LocalDate purchaseDate;
    private int totalPlayers;
    private String category;
    private int recommendedAge;
    private int averagePlayTime;
    private String description;
    private String memberComment;

    public Game() {
    }

    public Game(int gameId, String gameName, LocalDate purchaseDate, int totalPlayers, String category, int recommendedAge, String description, String memberComment) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.purchaseDate = purchaseDate;
        this.totalPlayers = totalPlayers;
        this.category = category;
        this.recommendedAge = recommendedAge;
        this.description = description;
        this.memberComment = memberComment;
    }

    public String getGameName() {
        return gameName;
    }

//    @OneToMany(mappedBy = "game")
//    private List<GameSession> sessions;
}
