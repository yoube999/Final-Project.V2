package tw.com.eeit168.member.dao;

import tw.com.eeit168.member.model.MemberProfileBean;

public interface MemberProfileInterFace {

	MemberProfileBean select(int member_profile_id);

	
	public abstract void insert(MemberProfileBean memberProfileBean);
}
