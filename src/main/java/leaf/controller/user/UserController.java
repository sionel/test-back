package leaf.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.amazonaws.services.s3.model.S3ObjectSummary;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import leaf.controller.S3Uploader;
import leaf.model.member.MemberList;
import leaf.service.member.MemberService;

@RestController
@CrossOrigin("origin-allowed=*")
@RequestMapping(value = "/user")
public class UserController {

    private final MemberService service;
    private final S3Uploader s3uploader;

    @Autowired
    public UserController(MemberService service, S3Uploader s3uploader) {
        this.service = service;
        this.s3uploader = s3uploader;
    }

    // 진짜 코드 (수정 금지)
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public MemberList register() {
        System.out.println("여기");
        return new MemberList();
    }

    // 테스트 코드 (수정 가능)
    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public MemberList register1() {
        System.out.println("여기");
        return service.getModel("d346663zxc");
    }

    @RequestMapping(value = "/register1", method = RequestMethod.POST)
    public Map<String, String> register12(@RequestBody MemberList model) {
        Map<String, String> map = new HashMap<String, String>();
        System.out.println(model);
        // service.register(model);
        map.put("message", "success");
        return map;
    }

    @RequestMapping(value = "/reg1", method = RequestMethod.POST)
    public Map<String, Object> register2(@RequestBody String id) {
        System.out.println("@@@reg1@@@");
        System.out.println(id);
        System.out.println("@@@@");
        System.out.println("idid");
        JSONObject json = new JSONObject(id);
        System.out.println(json.getString("data1"));
        Map<String, Object> map = new HashMap<String, Object>();
        Vector<String> v = new Vector<String>();
        v.add("a");
        v.add("b");
        v.add("c");
        v.add("d");
        map.put("message", v);
        return map;
    }

    @RequestMapping(value = "/s3put", method = RequestMethod.GET)
    public void put() {
        String filePath = "C:\\Users\\BIT\\Downloads\\book2.png";
        s3uploader.putObject("dz-leaf", filePath);
    }

    @RequestMapping(value = "/s3delete", method = RequestMethod.GET)
    public void delete() {
        s3uploader.deleteObject("dz-leaf", "book.png");
    }

    @RequestMapping(value = "/s3getallobject", method = RequestMethod.GET)
    public List<S3ObjectSummary> getAllObject() {
        return s3uploader.getAllObject("dz-leaf");
    }

}
