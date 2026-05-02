package se.yrgo.service;

import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.data.MemberDao;
import se.yrgo.domain.Member;

import java.util.List;

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
            throw new RuntimeException("The member with id " + id + " could not be found.");
        }
    }
}
