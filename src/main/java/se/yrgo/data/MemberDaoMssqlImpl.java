package se.yrgo.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import se.yrgo.domain.Member;

import java.util.List;

@Repository
public class MemberDaoMssqlImpl implements MemberDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Member> getAllMembers() {
        return em.createQuery("SELECT member FROM Member AS member", Member.class)
                .getResultList();
    }
}
