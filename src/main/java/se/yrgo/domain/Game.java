package se.yrgo.domain;

import jakarta.persistence.*;
import org.jspecify.annotations.NonNull;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a board game owned by the club library.
 * <p>
 * A game entity stores information about games all
 * can be used in game session in the game sessions by members.
 */

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameId;

    @Column(unique = true, nullable = false)
    private String gameName;
    @Column(nullable = false)
    private LocalDate purchaseDate;
    private int totalPlayers;
    private String category;
    private int recommendedAge;
    private int averagePlayTime;
    private String description;
    private String memberComment;

    /**
     * Default constructor required by JPA
     */
    public Game() {
    }

    /**
     * Creates a complete Game object including its database identifier.
     *
     * @param gameId          unique identifier of the game
     * @param gameName        name of the board game
     * @param purchaseDate    date when the game was purchased
     * @param totalPlayers    maximum number of supported players
     * @param category        game category of genre
     * @param recommendedAge  recommended minimum player age
     * @param averagePlayTime average duration of gameplay in minutes
     * @param description     description of the game
     * @param memberComment   member comments, reviews, recommends about the game
     *
     */
    public Game(int gameId, @NonNull String gameName, @NonNull LocalDate purchaseDate, int totalPlayers, String category, int recommendedAge, int averagePlayTime, String description, String memberComment) {
        if (recommendedAge < 0 || averagePlayTime < 0 || description.isEmpty()) {
            throw new IllegalArgumentException("recommendedAge or averagePlayTime cannot be negative");
        }
        if (gameName.isEmpty()) {
            throw new IllegalArgumentException("Game name cannot be empty");
        }
        this.gameId = gameId;
        this.gameName = gameName;
        this.purchaseDate = purchaseDate;
        this.totalPlayers = totalPlayers;
        this.category = category;
        this.recommendedAge = recommendedAge;
        this.averagePlayTime = averagePlayTime;
        this.description = description;
        this.memberComment = memberComment;
    }

    /**
     * Creates a new Game object without a database identifier.
     * <p>
     * This constructor is typically used when creating a new game before it is persisted to the database.
     *
     * @param gameName        name of the board game
     * @param purchaseDate    date when the game was purchased
     * @param totalPlayers    maximum number of supported players
     * @param category        game category of genre
     * @param recommendedAge  recommended minimum player age
     * @param averagePlayTime average duration of gameplay in minutes
     * @param description     description of the game
     * @param memberComment   member comments, reviews, recommends about the game
     *
     */
    public Game(@NonNull String gameName, @NonNull LocalDate purchaseDate, int totalPlayers, String category, int recommendedAge, int averagePlayTime, String description, String memberComment) {
        this.gameName = gameName;
        this.purchaseDate = purchaseDate;
        this.totalPlayers = totalPlayers;
        this.category = category;
        this.recommendedAge = recommendedAge;
        this.averagePlayTime = averagePlayTime;
        this.description = description;
        this.memberComment = memberComment;
    }

    public String getGameName() {
        return gameName;
    }

    public int getGameId() {
        return gameId;
    }

    public int getRecommendedAge() {
        return recommendedAge;
    }

    public String getDescription() {
        return description;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getTotalPlayers() {
        return totalPlayers;
    }

    public void setTotalPlayers(int totalPlayers) {
        this.totalPlayers = totalPlayers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRecommendedAge(int recommendedAge) {
        this.recommendedAge = recommendedAge;
    }

    public int getAveragePlayTime() {
        return averagePlayTime;
    }

    public void setAveragePlayTime(int averagePlayTime) {
        this.averagePlayTime = averagePlayTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMemberComment() {
        return memberComment;
    }

    public void setMemberComment(String memberComment) {
        this.memberComment = memberComment;
    }
}
