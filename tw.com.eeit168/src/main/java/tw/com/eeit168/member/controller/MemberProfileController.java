package tw.com.eeit168.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import tw.com.eeit168.member.model.MemberProfileBean;
import tw.com.eeit168.member.service.MemberProfileService;

@RestController
@RequestMapping("/eeit168/member")
@CrossOrigin
public class MemberProfileController {

	@Autowired
	private MemberProfileService memberProfileService;

	@Autowired
	private MessageSource messageSource;

	  @PostMapping("/login")
	    public ResponseEntity<Object> login(@RequestBody JsonNode jsonNode) {
	        MemberProfileBean memberProfileBean = memberProfileService.login(jsonNode);
	        if (memberProfileBean != null) {
	            return ResponseEntity.ok(memberProfileBean); // 登录成功，返回完整的 MemberProfileBean 对象
	        } else {
	            String errorMessage = "登入失敗";
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage); // 登录失败，返回 401 未授权状态和错误消息
	        }
	    }
	  
	  @PostMapping("/fetchMemberInfo")
	    public ResponseEntity<Object> fetchMemberInfo(@RequestBody JsonNode jsonNode) {
	        MemberProfileBean memberProfileBean = memberProfileService.fetchMemberInfo(jsonNode);
	        if (memberProfileBean != null) {
	            return ResponseEntity.ok(memberProfileBean); // 登录成功，返回完整的 MemberProfileBean 对象
	        } else {
	            String errorMessage = "查詢失敗";
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage); // 登录失败，返回 401 未授权状态和错误消息
	        }
	    }
	
	// 註冊送Post送Json
	@PostMapping("/register")
	public String register(@RequestBody JsonNode jsonNode,
			@RequestParam(name = "setCustomer", required = false) boolean setCustomerService) {
		try {
			memberProfileService.registerMember(jsonNode, setCustomerService);	// 一般會員註冊時不送setCustomerService
			return "註冊成功";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	// 發送驗證碼Post送字串
	@PostMapping("/send-verification-code")
	public String sendVerificationCode(@RequestParam String user_account) {
		try {
			memberProfileService.sendVerificationCode(user_account);
			return "驗證碼郵件已發送";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	// 輸入验证码后修改密码 Post送字串
	@PostMapping("/change-password-with-verification-code")
	public String changePasswordWithVerificationCode(@RequestParam String user_account,
	        @RequestParam String verification_code, @RequestParam String user_password) {
	    try {
	        memberProfileService.changePasswordWithVerificationCode(user_account, verification_code, user_password);
	        return "密碼更改成功！"; // 無論成功或失敗，都返回相同的成功消息
	    } catch (Exception e) {
	        return "密碼更改失敗";
	    }
	}

	 @PostMapping("/update")
    public ResponseEntity<String> updateMemberInfo(@RequestBody JsonNode jsonNode) {
        try {
            // 添加 JSON 数据验证逻辑
            // 检查是否存在 user_password 字段
            if (!jsonNode.has("user_password")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("缺少 user_password 字段");
            }
            // 检查是否存在 gender 字段
            if (!jsonNode.has("gender")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("缺少 gender 字段");
            }
            // 检查 user_password 字段的值是否为字符串
            if (!jsonNode.get("user_password").isTextual()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user_password 字段的值不是字符串");
            }
            // 检查 gender 字段的值是否为字符串
            if (!jsonNode.get("gender").isTextual()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("gender 字段的值不是字符串");
            }

            // 从 JSON 中获取 user_account
            String user_account = jsonNode.get("user_account").asText();

            memberProfileService.updateMemberInfo(user_account, jsonNode);
            return ResponseEntity.ok("會員資料更新成功");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("會員資料更新失敗");
        }
    }
}

//	@GetMapping("/profile")
//	public ResponseEntity<Object> getProfileInfo(@CookieValue(name = "member_profile_id", required = false) Integer member_profile_id) {
//	    if (member_profile_id == null) {
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("无法获取 member_profile_id");
//	    }
//
//	    // 使用 member_profile_id 來查詢會員資料
//	    MemberProfileBean profile = memberProfileService.getProfileInfo(member_profile_id);
//	    if (profile != null) {
//	        return ResponseEntity.ok(profile);
//	    } else {
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("找不到会员信息");
//	    }