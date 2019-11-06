
package leaf.service.member;

import leaf.db.member.MemberDAO;
import leaf.model.member.MemberList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberDAO dao;

    @Override
    public boolean register(MemberList model) {
        try {
            dao.saveAndFlush(model);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // @Override
    // public MemberList login(String id) {
    //     return dao.getOne(id);
    // }

    @Override
    public MemberList getModel(String id) {
        return dao.findById(id).get();
    }

    @Override
    public String idCheck(String id) {
        try {
            dao.findById(id).get();
            return "fail";
        } catch (Exception e) {
            return "success";
        }
    }

    // 전달받은 ID에게 발급해준 토큰이 있는지 확인
    @Override
    public boolean hasToken(String id) {
        return true;
    }

    // 로그인 요청 시 회원의 ID와 Password가 맞는지 확인
    @Override 
    public boolean isMemberExist(String id, String pw) {
        return !dao.findByMemberIdAndMemberPw(id, pw).isEmpty();
    }

    @Override
    public String findId(String name, String email) {
        try {
            return dao.findByMemberNmAndEmail(name, email).get(0).getMemberId();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String findPw(String id, String email) {
        try {
            return dao.findByMemberIdAndEmail(id, email).get(0).getMemberPw();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public boolean changePw(String id, String pw) {
        try {
            System.out.println("1");
            MemberList list = dao.getOne(id);
            System.out.println("2");
            System.out.println(pw);
            System.out.println("3");
            System.out.println(list.getMemberPw());
            System.out.println("4");
            if (list.getMemberPw().equals(pw)) return false;
            System.out.println("5");
            list.setMemberPw(pw);
            dao.save(list);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
