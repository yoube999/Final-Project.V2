package tw.com.eeit168.member.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.eeit168.member.model.MemberProfileBean;





@SpringBootTest
public class MemberProfileTest {
	
	@Autowired
	private MemberProfileInterFace memberProfileInterFace;
	
	@Test
	public void testselect() {
		MemberProfileBean bean = memberProfileInterFace.select(4);
		System.out.println(bean);
	}
	
}
