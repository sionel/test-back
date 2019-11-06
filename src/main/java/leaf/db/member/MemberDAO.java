package leaf.db.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import leaf.model.member.MemberList;

public interface MemberDAO extends JpaRepository<MemberList, String> {

    // @Query(nativeQuery = true, value = "SELECT * FROM member.member_list WHERE userId")
    // memberList findByUsername(String userId);

    List<MemberList> findByCompanyNmLike(String companyNm);

    List<MemberList> findByMemberNmAndEmail(String memberNm, String email);

    List<MemberList> findByMemberIdAndEmail(String memberId, String email);

    List<MemberList> findByMemberIdAndMemberPw(String memberId, String memberPw);

    MemberList getOne(String id);

}
