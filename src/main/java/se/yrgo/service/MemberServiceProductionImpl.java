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
    public void createMember(Member newMember) {
        try {
            memberDao.createMember(newMember);
        } catch (EntityExistsException e) {
            throw new RuntimeException("This member already exists.", e);
        }
    }
}
