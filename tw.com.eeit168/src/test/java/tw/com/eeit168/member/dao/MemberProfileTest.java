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
	
	@Test
	public void testselect() {


		MemberProfileBean bean = memberProfileInterFace.select("user1@example.com");
		System.out.println(bean);

	}
	
	
//	@Test
	public void testInsert() throws ParseException {
	    MemberProfileBean newMember = new MemberProfileBean();
	    newMember.setUser_account("example_account");
	    newMember.setUser_password("example_password");
	    newMember.setUsername("John Doe");

	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date birthday = sdf.parse("2000-01-01");
	    newMember.setBirthday(birthday);

	    newMember.setGender("Male");
	    newMember.setPhone_number("1234567890");

	    Date registrationDate = sdf.parse("2023-09-22");
	    newMember.setRegistration_date(registrationDate);

	    newMember.setAccount_status("Active");
	    newMember.setMember_level(2);
	    newMember.setVerification_code("123456");
	    newMember.setAttempts(0);

	    memberProfileInterFace.insert(newMember);
	}
	
}