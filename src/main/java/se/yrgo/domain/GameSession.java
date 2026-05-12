package se.yrgo.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Represents a single play session of a board game in the club system.
 * <p>
 * A GameSession stores information about when a specific game was played.
 * Each session is linked to one {@link Game}, and represents a historical
 * record of gameplay events.
 * <p>
 * This entity can later be extended to include participants, scores,
 * and winners of the session.
 */

@Entity
public class GameSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sessionId")
    private int sessionId;

    /**
     * The game that was played during this session.
     * Many sessions can be associated with one game.
     */
    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    /**
     * The date when this game session took place.
     */
    @Column(name = "date", nullable = false)
    private LocalDate date;

    public GameSession() {
    }

    /**
     * Creates a new GameSession for a specific game and date.
     *
     * @param game the board game played in this session
     * @param date the date of the session
     */
    public GameSession(Game game, LocalDate date) {
        this.game = game;
        this.date = date;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
