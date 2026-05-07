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
    public Member getById(int id) {
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
    public void deleteMember(int id) {
        try {
            Member memberToDelete = getById(id);
            memberDao.deleteMember(memberToDelete);
        } catch (MemberNotFoundException e){
            throw new MemberNotFoundException("Unable to delete:" + e.getMessage());
        }
    }

    @Override
    public void updateMember(int id, String name, String email, Boolean membershipFeePaid, Integer totalWins, Integer age) {
        Member memberToUpdate = getById(id);

        if (name != null) memberToUpdate.setName(name);
        if (email != null) memberToUpdate.setEmail(email);
        if (membershipFeePaid != null) memberToUpdate.setMembershipFeePaid(membershipFeePaid);
        if (totalWins != null) memberToUpdate.setTotalWins(totalWins);
        if (age != null) memberToUpdate.setAge(age);

        memberDao.updateMember(memberToUpdate);
    }
}
