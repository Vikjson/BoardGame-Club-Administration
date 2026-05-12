package se.yrgo.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.data.MemberDao;
import se.yrgo.domain.Member;
import se.yrgo.error.MemberNotFoundException;

import java.util.List;

/**
 * Production implementation of {@link MemberService}.
 * <p>
 * Provides business logic for managing Game entities including retrieval,
 * creation, updating, and deletion operations.
 * Acts as an intermediary layer between controllers and the DAO layer.
 * <p>
 * This service is also responsible for:
 * <ul>
 *     <li>Mapping persistence-layer exceptions into application-specific exceptions</li>
 *     <li>Validating business rules before delegating to DAO</li>
 *     <li>Managing transactional operations</li>
 * </ul>
 */
@Transactional
@Service
public class MemberServiceProductionImpl implements MemberService{
    private MemberDao memberDao;

    @Autowired
    public MemberServiceProductionImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public List<Member> getAllClubMembers() {
        return memberDao.getAllMembers();
    }

    @Override
    public Member getById(Integer id) {
        try {
            return memberDao.getById(id);
        }
        catch (NoResultException e) {
            throw new MemberNotFoundException("The member with id " + id + " could not be found.");
        }
    }

    @Override
    public Member getByEmail(String email) {
        try {
            return memberDao.getByEmail(email);
        }
        catch (NoResultException e) {
            throw new MemberNotFoundException("The member with email " + email + " could not be found.");
        }
    }

    @Override
    public void createMember(Member newMember) {
        try {
            memberDao.createMember(newMember);
        } catch (EntityExistsException e) {
            throw new RuntimeException("This member already exists.", e);
        }
    }

    @Override
    public void deleteMember(Integer id) {
        try {
            Member memberToDelete = getById(id);
            memberDao.deleteMember(memberToDelete);
        } catch (MemberNotFoundException e){
            throw new MemberNotFoundException("Unable to find member:" + e.getMessage());
        }
    }

    @Override
    public void updateMember(Integer id, Member memberUpdate) {
        Member memberFromDbToUpdate = getById(id);

        if (memberUpdate.getName() != null) memberFromDbToUpdate.setName(memberUpdate.getName());
        if (memberUpdate.getEmail() != null) memberFromDbToUpdate.setEmail(memberUpdate.getEmail());
        if (memberUpdate.getMembershipFeePaid() != null) memberFromDbToUpdate.setMembershipFeePaid(memberUpdate.getMembershipFeePaid());
        if (memberUpdate.getTotalWins() != null) memberFromDbToUpdate.setTotalWins(memberUpdate.getTotalWins());
        if (memberUpdate.getAge() != null) memberFromDbToUpdate.setAge(memberUpdate.getAge());

        memberDao.updateMember(memberFromDbToUpdate);
    }
}
