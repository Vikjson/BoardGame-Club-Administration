package se.yrgo.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class GameSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sessionId")
    private int sessionId;


    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    @Column(name = "date", nullable = false)
    private LocalDate date;
    /* 

    @OneToMany(mappedBy = "gameSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessionParticipant> participants = new ArrayList<>();

     */

    public GameSession() {
    }

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

    /* 
   
    public List<SessionParticipant> getParticipants() {
        return participants;
    }

    

    public void addParticipant(Member member, int score, boolean winner) {
        SessionParticipant participant = new SessionParticipant(member.getMemberId(), sessionId, score, winner);

        participants.add(participant);
    }

*/


}
