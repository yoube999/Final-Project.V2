package tw.com.eeit168.helpdesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.core.net.SyslogOutputStream;
import tw.com.eeit168.helpdesk.dao.HelpDeskMemberDAOImpl;
import tw.com.eeit168.helpdesk.repository.HelpDeskMemberRepository;
import tw.com.eeit168.member.model.MemberProfileBean;

@Service
@Transactional(rollbackFor = { Exception.class })
public class HelpDeskMemberService {

	@Autowired
	private HelpDeskMemberDAOImpl helpDeskMemberDAOImpl;

	@Autowired
	private HelpDeskMemberRepository helpDeskMemberRepository;

	/**
	 * 前端點擊編輯客服人員頁面時，顯示客服人員清單
	 * 
	 * @param memberLevel: 固定查詢條件為99和100
	 * 
	 * @return List<MemberProfileBean>
	 */
	public List<MemberProfileBean> selectCustomerService(List<Integer> levels, int start, int row) {
		if (levels != null) {
			return helpDeskMemberDAOImpl.selectCustomerService(levels, start, row);
		}
		return null;
	}

	/**
	 * 前端點擊刪除客服人員
	 * 
	 * @param member_profile_id: 客服人員ID
	 * 
	 * @return boolean
	 */
	public boolean removeCustomerService(Integer memberProfileId) {

		Integer member = memberProfileId;

		// 如果前端未送memberProfileId時回傳false
		if (member != null) {
			MemberProfileBean removeId = helpDeskMemberRepository.findById(member).orElse(null);
			
			// 如果前端送memberProfileId為非客服人員回傳false
			if (removeId != null && removeId.getMember_level() == 99) {
				helpDeskMemberRepository.deleteById(memberProfileId);
				return true;
			}
		}
		return false;
	}

}
