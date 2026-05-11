package se.yrgo.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public void updateMember(Integer id, Member member) {
        Member memberToUpdate = getById(id);

        if (member.getName() != null) memberToUpdate.setName(member.getName());
        if (member.getEmail() != null) memberToUpdate.setEmail(member.getEmail());
        if (member.getMembershipFeePaid() != null) memberToUpdate.setMembershipFeePaid(member.getMembershipFeePaid());
        if (member.getTotalWins() != null) memberToUpdate.setTotalWins(member.getTotalWins());
        if (member.getAge() != null) memberToUpdate.setAge(member.getAge());

        memberDao.updateMember(memberToUpdate);
    }
}
