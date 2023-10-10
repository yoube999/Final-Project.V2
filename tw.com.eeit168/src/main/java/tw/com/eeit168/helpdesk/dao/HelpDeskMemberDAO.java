package tw.com.eeit168.helpdesk.dao;

import java.util.List;

import tw.com.eeit168.member.model.MemberProfileBean;

public interface HelpDeskMemberDAO {

	public List<MemberProfileBean> selectCustomerService(List<Integer> levels, int start, int row);

}
