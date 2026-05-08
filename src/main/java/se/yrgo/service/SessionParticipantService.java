package se.yrgo.service;

import se.yrgo.domain.SessionParticipant;

import java.util.List;

public interface SessionParticipantService {

    void createSessionParticipant(SessionParticipant sessionParticipant);

    SessionParticipant getById(int participantId);

    List<SessionParticipant> getByMemberId(int id);

    void deleteSessionParticipant(int id);

    List<SessionParticipant> getAllSessionParticipants();

    List<SessionParticipant> getBySessionId(int sessionId);
}
