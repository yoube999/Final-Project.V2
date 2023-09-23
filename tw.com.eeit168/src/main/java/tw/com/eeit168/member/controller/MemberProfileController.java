package tw.com.eeit168.member.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public String register(@RequestParam String user_account,
			@RequestParam String user_password,
			@RequestParam String username, 
			@RequestParam String gender, 
			@RequestParam String phone_number,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday) {

		memberProfileService.registerMember(user_account, user_password, username, gender, phone_number, birthday);

		return "註冊成功";
	}
}
