package se.yrgo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import se.yrgo.domain.SessionParticipant;

import java.util.List;

public class SessionParticipantDapMssqlImpl implements SessionParticipantDao {
    @PersistenceContext
    private EntityManager em;
    @Override
    public void createSessionParticipant(SessionParticipant sessionParticipant) {
    }

    @Override
    public SessionParticipant getById(int participantId) {
        return em.createQuery("SELECT sp FROM SessionParticipant AS sp WHERE sp.id = :id", SessionParticipant.class)
                .getSingleResult();
    }

    @Override
    public void deleteSessionParticipant(SessionParticipant sessionParticipant) {

    }

    @Override
    public List<SessionParticipant> getAllSessionParticipants() {
            return em.createQuery("SELECT sp FROM SessionParticipant AS sp", SessionParticipant.class)
                    .getResultList();
    }
}
