package se.yrgo.service;

import se.yrgo.domain.GameSession;

import java.time.LocalDate;
import java.util.List;

public interface GameSessionService {

    List<GameSession> getAllGameSessions();

    GameSession getById(int id);

    List<GameSession> getByGameId(int gameId);

    List<GameSession> getByDate(LocalDate date);

    GameSession createGameSession(int gameId, LocalDate date);

    void updateGameSession(int id, Integer gameId, LocalDate date);

    void deleteGameSession(int id);

}