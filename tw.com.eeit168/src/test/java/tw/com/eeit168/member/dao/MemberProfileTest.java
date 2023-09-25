package tw.com.eeit168.member.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import tw.com.eeit168.member.model.MemberProfileBean;


@SpringBootTest
public class MemberProfileTest {
	
	@Autowired
	private MemberProfileInterFace memberProfileInterFace;
	
//	@Test
	public void testselect() {


		MemberProfileBean bean = memberProfileInterFace.select("user1@example.com");
		System.out.println(bean);

	}
	
	
//	@Test
	public void testInsert() throws ParseException {
	    MemberProfileBean newMember = new MemberProfileBean();
	    newMember.setUser_account("to60412@gmail.com");
	    newMember.setUser_password("password");
	    newMember.setUsername("John Doe");

	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date birthday = sdf.parse("2000-01-01");
	    newMember.setBirthday(birthday);

	    newMember.setGender("Male");
	    newMember.setPhone_number("999999999");

        Date registrationDate = new Date();
        newMember.setRegistration_date(registrationDate);

	    newMember.setAccount_status("Active");
	    newMember.setMember_level(2);
	    newMember.setVerification_code(null);
	    newMember.setAttempts(0);

	    memberProfileInterFace.insert(newMember);
	}
	
	
	
	
	@Test
	public void testChangePasswordWithVerificationCode() {
	    // 假设您已经发送了验证码给会员并收到了输入的验证码
	    String verificationCode = "204726"; // 假设收到的验证码为 "123456"

	    // 假设您要更改的会员帐户是 "accountToUpdate"
	    String userAccountToUpdate = "to60412@gmail.com";

	    try {
	        // 获取要更改密码的会员数据
	        MemberProfileBean existingMember = memberProfileInterFace.select(userAccountToUpdate);
	        // 检查是否找到要更改密码的会员数据
	        if (existingMember != null) {
	            // 检查输入的验证码是否匹配
	            if (verificationCode.equals(existingMember.getVerification_code())) {
	                // 输入的验证码匹配，可以更改密码
	                // 假设您要更改的新密码为 "newPassword"
	                String newPassword = "741741";

	                // 设置新密码
	                existingMember.setUser_password(newPassword);

	                // 清除验证码（一次性验证码可能需要在使用后清除）
	                existingMember.setVerification_code(null);

	                // 更新密码和清除验证码
	                memberProfileInterFace.update(existingMember);

	                // 检查密码是否已成功更改
	                if (existingMember.getUser_password().equals(newPassword)) {
	                    System.out.println("密码已成功更改！");
	                } else {
	                    throw new RuntimeException("密码未成功更改");
	                }
	            } else {
	                // 输入的验证码不匹配，抛出异常
	                throw new RuntimeException("驗證碼錯誤");
	            }
	        } else {
	            // 没有找到要更改密码的会员数据，抛出异常
	            throw new RuntimeException("找不到要更改密码的会员");
	        }
	    } catch (RuntimeException e) {
	        // 打印异常消息到控制台
	        System.out.println("异常消息：" + e.getMessage());
	        // 可以选择重新抛出异常以供JUnit捕获和处理
	        throw e;
	    }
	}
}