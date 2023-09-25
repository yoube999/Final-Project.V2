package tw.com.eeit168.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import tw.com.eeit168.member.service.MemberProfileService;

@RestController
@RequestMapping("/eeit168/member")
public class MemberProfileController {

    @Autowired
    private MemberProfileService memberProfileService;

    @Autowired
    private MessageSource messageSource;

    // 前端Post送字串
    @PostMapping("/login")
    public String login(@RequestParam String user_account, String user_password) {

        boolean success = memberProfileService.login(user_account, user_password);
        if (success) {
            return "登入成功";
        } else {
            return "登入失敗";
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody JsonNode jsonNode) {
        try {
            memberProfileService.registerMember(jsonNode);
            return "註冊成功";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //發送驗證碼
    @PostMapping("/send-verification-code")
    public String sendVerificationCode(@RequestParam String user_account) {
        try {
            memberProfileService.sendVerificationCode(user_account);
            return "驗證碼郵件已發送";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    
    
    //輸入驗證碼後修改密碼
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String user_account, @RequestParam String verification_code, @RequestParam String user_password) {
        try {
            // 先验证验证码是否正确
            boolean codeVerified = memberProfileService.verifyVerificationCode(user_account, verification_code);
            if (codeVerified) {
                // 验证通过，设置新密码
                boolean success = memberProfileService.resetPassword(user_account,verification_code, user_password);
                if (success) {
                    return "密碼重置成功";
                } else {
                    return "密碼重置失敗";
                }
            } else {
                return "驗證碼不正確";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
