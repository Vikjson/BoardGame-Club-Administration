package se.yrgo.data;

import se.yrgo.domain.Member;

import java.util.List;

public interface MemberDao {
    List<Member> getAllMembers();
    Member getById(int id);
    void createMember(Member newMember);
}
