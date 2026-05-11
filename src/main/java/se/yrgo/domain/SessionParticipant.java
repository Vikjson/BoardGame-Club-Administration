package se.yrgo.domain;

import jakarta.persistence.*;

@Entity
public class SessionParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "sessionId")
    private GameSession session;

    private int score;
    private boolean isWinner;

    public SessionParticipant(Member member, GameSession session, int id, int score, boolean isWinner) {
        this.member = member;
        this.session = session;
        this.id = id;
        this.score = score;
        this.isWinner = isWinner;
    }

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
    }
}
