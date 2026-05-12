package se.yrgo.domain;

import jakarta.persistence.*;

/**
 * Represents the participation of a {@link Member} in a {@link GameSession}.
 * <p>
 * This entity acts as a join table with additional attributes, storing
 * information about a member's performance in a specific game session,
 * including score and whether the member won the session.
 * <p>
 * It models a many-to-many relationship between Member and GameSession
 * with extra metadata (score, winner).
 */
@Entity
public class SessionParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The member who participated in the game session.
     */
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    /**
     * The game session in which the member participated.
     */
    @ManyToOne
    @JoinColumn(name = "sessionId")
    private GameSession session;

    private int score;
    private boolean isWinner;

    /**
     * Creates a SessionParticipant with a generated database ID.
     *
     * @param member   the member participating in the session
     * @param session  the game session
     * @param id       unique identifier (database-generated or manually assigned)
     * @param score    score achieved in the session
     * @param isWinner whether the member won the session
     */
    public SessionParticipant(Member member, GameSession session, int id, int score, boolean isWinner) {
        this.member = member;
        this.session = session;
        this.id = id;
        this.score = score;
        this.isWinner = isWinner;
    }

    /**
     * Creates a SessionParticipant without specifying an ID.
     * <p>
     * Typically used when the entity is newly created and will be persisted
     * to the database, where the ID is generated automatically.
     *
     * @param member   the member participating in the session
     * @param session  the game session
     * @param score    score achieved in the session
     * @param isWinner whether the member won the session
     */
    public SessionParticipant(Member member, GameSession session, int score, boolean isWinner) {
        this.member = member;
        this.session = session;
        this.score = score;
        this.isWinner = isWinner;
    }

    public SessionParticipant() {
    }

    public int getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

     public GameSession getGameSession() {
        return session;
    }

    public int getScore() {
        return score;
    }
    public boolean isWinner() {
        return isWinner;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setGameSession(GameSession session) {
        this.session = session;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public void setId(int id) {
        this.id = id;
    }
}
