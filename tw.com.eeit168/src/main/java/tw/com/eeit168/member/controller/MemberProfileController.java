package tw.com.eeit168.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import tw.com.eeit168.member.service.MemberProfileService;

@RestController
@RequestMapping("/eeit168/member")
@CrossOrigin
public class MemberProfileController {

    @Autowired
    private MemberProfileService memberProfileService;

    @Autowired
    private MessageSource messageSource;

    //登入Post送字串
    @PostMapping("/login")
    public String login(@RequestParam String user_account, String user_password) {

        boolean success = memberProfileService.login(user_account, user_password);
        if (success) {
            return "登入成功";
        } else {
            return "登入失敗";
        }
    }
    
    
    //註冊送Post送Json
    @PostMapping("/register")
    public String register(@RequestBody JsonNode jsonNode) {
        try {
            memberProfileService.registerMember(jsonNode);
            return "註冊成功";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //發送驗證碼Post送字串
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
                                                     @RequestParam String verification_code,
                                                     @RequestParam String user_password) {
        try {
            memberProfileService.changePasswordWithVerificationCode(user_account, verification_code, user_password);
            return "密碼更改成功！";
        } catch (Exception e) {
            return e.getMessage();
        }
    }}

 