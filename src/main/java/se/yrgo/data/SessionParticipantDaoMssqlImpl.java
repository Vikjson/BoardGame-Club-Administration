package se.yrgo.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Repository;
import se.yrgo.domain.SessionParticipant;

import java.util.List;

@Repository
public class SessionParticipantDaoMssqlImpl implements SessionParticipantDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void createSessionParticipant(SessionParticipant sessionParticipant) {
        em.persist(sessionParticipant);
    }

    @Override
    public SessionParticipant getById(int participantId) {
        return em.createQuery("SELECT sp FROM SessionParticipant AS sp WHERE sp.id = :id", SessionParticipant.class)
                .setParameter("id", participantId)
                .getSingleResult();
    }

    @Override
    public void deleteSessionParticipant(SessionParticipant sessionParticipant) {
        SessionParticipant managed = em.merge(sessionParticipant);
        em.remove(managed);
    }

    @Override
    public SessionParticipant updateSessionParticipant(SessionParticipant sessionParticipant) {
       return em.merge(sessionParticipant);
    }

    @Override
    public List<SessionParticipant> getAllSessionParticipants() {
        return em.createQuery("SELECT sp FROM SessionParticipant AS sp", SessionParticipant.class)
                .getResultList();
    }

    @Override
    public List<SessionParticipant> getBySessionId(int sessionId) {
        return em.createQuery(
                        "SELECT sp FROM SessionParticipant AS sp WHERE sp.session.sessionId = :sessionId",
                        SessionParticipant.class
                )
                .setParameter("sessionId", sessionId)
                .getResultList();
    }

    @Override
    public List<SessionParticipant> getByMemberId(int memberId) {
        return em.createQuery(
                        "SELECT sp FROM SessionParticipant AS sp WHERE sp.member.memberId = :memberId",
                        SessionParticipant.class
                )
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
