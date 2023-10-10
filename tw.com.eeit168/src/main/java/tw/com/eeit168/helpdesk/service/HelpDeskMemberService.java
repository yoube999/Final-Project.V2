package tw.com.eeit168.helpdesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.helpdesk.dao.HelpDeskMemberDAOImpl;
import tw.com.eeit168.member.model.MemberProfileBean;

@Service
@Transactional(rollbackFor = { Exception.class })
public class HelpDeskMemberService {


	@Autowired
	private HelpDeskMemberDAOImpl helpDeskMemberDAOImpl;

	/**
	 * 前端點擊編輯客服人員頁面時，顯示客服人員清單
	 * 
	 * @param memberLevel: 固定查詢條件為99和100
	 * 
	 * @return List<MemberProfileBean>
	 */
	public List<MemberProfileBean> selectCustomerService(List<Integer> levels, int start, int row){
		if(levels !=null) {
			return helpDeskMemberDAOImpl.selectCustomerService(levels, start, row);
		}
		return null;
	}
}
