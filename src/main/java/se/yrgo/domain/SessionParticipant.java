package se.yrgo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SessionParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int memberId;
    private int sessionId;
    private int score;
    private boolean isWinner;

    public SessionParticipant(int id, int memberId, int sessionId, int score, boolean isWinner) {
        this.id = id;
        this.memberId = memberId;
        this.sessionId = sessionId;
        this.score = score;
        this.isWinner = isWinner;
    }

    public SessionParticipant(int memberId, int sessionId, int score, boolean isWinner) {
        this.memberId = memberId;
        this.sessionId = sessionId;
        this.score = score;
        this.isWinner = isWinner;
    }

    public SessionParticipant() {
    }

    public int getId() {
        return id;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public int getScore() {
        return score;
    }

    public boolean isWinner() {
        return isWinner;
    }
}
