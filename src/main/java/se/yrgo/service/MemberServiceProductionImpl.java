package se.yrgo.service;

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
}
