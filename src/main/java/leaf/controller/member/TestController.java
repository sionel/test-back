package leaf.controller.member;

import leaf.service.member.JwtServiceImpl;
import leaf.service.member.MemberService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
public class TestController {

    MemberService service;
    JwtServiceImpl jwtService;

    Map<String, Object> map = new HashMap<>();

    // @GetMapping("/logintest")
    // public Map<String, Object> loginTest(HttpServletResponse res, String id) {
    //     map.clear();
    //     if (service.hasToken(id)) {

    //     } else {
    //         String token = jwtService.createJwt(id);
    //         res.setHeader("Authorization", token);
    //         map.put("Authorization", token);
    //     }
    //     return map;
    // }

}
