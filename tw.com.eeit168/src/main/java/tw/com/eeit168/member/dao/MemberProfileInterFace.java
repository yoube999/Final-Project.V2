package tw.com.eeit168.member.dao;

import tw.com.eeit168.member.model.MemberProfileBean;

public interface MemberProfileInterFace {

	
	//登入
	MemberProfileBean select(String user_account);

	
	//註冊
	public abstract void insert(MemberProfileBean memberProfileBean);
	
	
	//忘記密碼的更新
	public abstract void update(MemberProfileBean member);
}
