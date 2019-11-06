package leaf.service.member;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JwtInterceptor implements HandlerInterceptor {

    JwtServiceImpl jwtService;
    
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        final String token = req.getHeader("Authorization");
        if (token != null && jwtService.isUsable(token)) {
            res.setHeader("Authorization", jwtService.createJwt((jwtService.getTokenValue("sub", req).toString())));
            return true;
        } else {
            throw new UnauthorizedException();
        }
        // DB에서 ID, PW 맞는지 확인하는 코드 여기에 작성
        // 맞다면 아래 로직 수행
        // String createdToken = req.getHeader("Authorization");
        // if (createdToken == null || jwtService.isUsable(createdToken)) {
        // String token = jwtService.createJwt("name_jh");
        // res.setHeader("Authorization", token);
        // HashMap<String, Object> map = new HashMap<>();
        // map.put("Authorization", token);
        // }
    }

}
