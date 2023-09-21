package tw.com.eeit168.member.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.member.model.MemberProfileBean;

@Repository
public class MemberProfileDAO implements MemberProfileInterFace {

	@PersistenceContext
	private Session session;
	public Session getSession(){
		return session;
	}
	
	@Override
	public MemberProfileBean select(int member_profile_id) {
		if(member_profile_id != 0) {
			return this.getSession().get(MemberProfileBean.class, member_profile_id);
		}
		return null;
	}
	
}
