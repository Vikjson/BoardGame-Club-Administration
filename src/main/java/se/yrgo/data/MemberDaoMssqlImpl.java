package se.yrgo.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import se.yrgo.domain.Member;

import java.util.List;

@Repository
public class MemberDaoMssqlImpl implements MemberDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Member> getAllMembers() {
        return em.createQuery("SELECT member FROM Member AS member", Member.class)
                .getResultList();
    }

    @Override
    public Member getById(int id) {
        return em.createQuery("SELECT member FROM Member AS member WHERE member.id = :id", Member.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Member getByEmail(String email) {
        return em.createQuery("SELECT member FROM Member AS member WHERE member.email = :email", Member.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public void createMember(Member newMember) {
        em.persist(newMember);
    }

    @Override
    public void deleteMember(Member memberToDelete) {
        em.remove(memberToDelete);
    }

    @Override
    public void updateMember(Member memberToUpdate) {
        em.merge(memberToUpdate);
    }
}
