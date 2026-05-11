package se.yrgo.data;

import se.yrgo.domain.SessionParticipant;

import java.util.List;

public interface SessionParticipantDao {
    void createSessionParticipant(SessionParticipant sessionParticipant);

    SessionParticipant getById(int participantId);

    void deleteSessionParticipant(SessionParticipant sessionParticipant);

    List<SessionParticipant> getAllSessionParticipants();

    List<SessionParticipant> getBySessionId(int sessionId);

    List<SessionParticipant> getByMemberId(int memberId);

}
