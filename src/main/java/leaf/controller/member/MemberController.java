package leaf.controller.member;

import leaf.controller.S3Uploader;
import leaf.model.member.MemberList;
import leaf.service.company.CompanyService;
import leaf.service.member.JwtServiceImpl;
import leaf.service.member.MemberService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.json.JSONObject;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

// import leaf.controller.S3Uploader;

@RestController
@CrossOrigin("origin-allowed=*")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(value = "/member")
public class MemberController {

    MemberService memberService;
    CompanyService companyService;
    JavaMailSender javaMailSender;
    JwtServiceImpl jwtService;
    S3Uploader s3uploader;

    Map<String, Object> map = new HashMap<>();

    // 진짜 코드 (수정 금지)

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, Object> register(@RequestBody MemberList model) {
        System.out.println("/member/register");
        map.clear();
        System.out.println(model);
        map.put("message", memberService.register(model) ? "success" : "fail");
        return map;
    }

    @RequestMapping(value = "/idCheck", method = RequestMethod.POST)
    public Map<String, Object> idCheck(@RequestBody Object obj) {
        System.out.println("/member/idCheck");
        System.out.println("도커된당");
        map.clear();
        map.put("message", memberService.idCheck(obj.toString()));

        return map;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login() {
        System.out.println("/member/login");
        map.clear();
        return map;
    }

    // @RequestMapping(value = "/login", method = RequestMethod.POST)
    // public Map<String, Object> login() {
    // System.out.println("/member/login");
    // return map;
    // }

    @RequestMapping(value = "/findCompany", method = RequestMethod.POST)
    public Map<String, Object> findCompany(@RequestBody Object obj) {
        System.out.println("/member/findCompany");
        map.clear();
        try {
            map.put("data", companyService.findCompany(obj.toString()));
            map.put("message", "success");
        } catch (Exception e) {
            map.put("message", "fail");
        }
        return map;
    }

    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public Map<String, Object> sendEmail(@RequestBody Object obj) {
        System.out.println("/member/sendEmail");
        map.clear();
        String email = obj.toString();
        try {
            String authNum = this.sendEmail(email);
            map.put("authNum", authNum);
            map.put("message", "success");
        } catch (Exception e) {
            System.out.println(e);
            map.put("message", "fail");
        }
        return map;
    }

    // 테스트 코드 (수정 가능)

    @RequestMapping(value = "/findIdAuthNm", method = RequestMethod.POST)
    public Map<String, Object> findIdAuthNm(@RequestBody String obj) {
        System.out.println("/member/findIdAuthNm");
        map.clear();
        JSONObject json = new JSONObject(obj);
        String name = json.getString("memberNm");
        String email = json.getString("email");
        System.out.println(name + " @@ " + email);
        if (memberService.findId(name, email) != null) {
            String authNum = this.sendEmail(email);
            map.put("authNum", authNum);
            map.put("message", "success");
        } else
            map.put("message", "fail");
        return map;
    }

    @RequestMapping(value = "/findId", method = RequestMethod.POST)
    public Map<String, Object> findId(@RequestBody String obj) {
        System.out.println("/member/findId");
        map.clear();
        JSONObject json = new JSONObject(obj);
        String name = json.getString("memberNm");
        String email = json.getString("email");
        map.put("memberId", memberService.findId(name, email));
        map.put("message", "success");
        return map;
    }

    @RequestMapping(value = "/findPwAuthNm", method = RequestMethod.POST)
    public Map<String, Object> findPwAuthNm(@RequestBody String obj) {
        System.out.println("/member/findPwAuthNm");
        map.clear();
        JSONObject json = new JSONObject(obj);
        String id = json.getString("memberId");
        String email = json.getString("email");
        System.out.println(id + " @@ " + email);
        if (memberService.findPw(id, email) != null) {
            String authNum = this.sendEmail(email);
            map.put("authNum", authNum);
            map.put("message", "success");
        } else
            map.put("message", "fail");
        return map;
    }

    @RequestMapping(value = "/changePw", method = RequestMethod.POST)
    public Map<String, Object> changePw(@RequestBody String obj) {
        System.out.println("/member/changePw");
        map.clear();
        JSONObject json = new JSONObject(obj);
        String id = json.getString("memberId");
        String pw = json.getString("memberPw");
        if (memberService.changePw(id, pw)) {
            map.put("message", "success");
        } else {
            map.put("message", "fail");
        }
        return map;
    }

    @GetMapping("/logintest")
    public Map<String, Object> loginTest(HttpServletResponse res, String id, String pw) {
        map.clear();
        if (memberService.isMemberExist(id, pw)) {
            String token = jwtService.createJwt(id);
            res.setHeader("Authorization", token);
            map.put("message", "success");
        } else {
            map.put("message", "fail");
        }
        return map;
    }

    // @RequestMapping(value = "/s3put", method = RequestMethod.GET)
    // public void put() {
    // String filePath = "C:\\Users\\BIT\\Downloads\\book2.png";
    // s3uploader.putObject("dz-leaf", filePath);
    // }

    // @RequestMapping(value = "/s3delete", method = RequestMethod.GET)
    // public void delete() {
    // s3uploader.deleteObject("dz-leaf", "book.png");
    // }

    // @RequestMapping(value = "/s3getallobject", method = RequestMethod.GET)
    // public List<S3ObjectSummary> getAllObject() {
    // return s3uploader.getAllObject("dz-leaf");
    // }

    private String sendEmail(String email) {
        int num = (int) Math.round(Math.random() * 100000);
        SimpleMailMessage msg = new SimpleMailMessage();
        System.out.println(email);
        msg.setTo(email);
        msg.setSubject("test");
        msg.setText("인증번호 : " + num);
        javaMailSender.send(msg);
        return Integer.toString(num);
    }

}
