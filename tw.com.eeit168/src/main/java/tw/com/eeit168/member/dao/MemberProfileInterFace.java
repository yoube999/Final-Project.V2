package tw.com.eeit168.member.dao;

import tw.com.eeit168.member.model.MemberProfileBean;

public interface MemberProfileInterFace {

	MemberProfileBean select(String user_account);

	
	public abstract void insert(MemberProfileBean memberProfileBean);
}
