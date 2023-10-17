package tw.com.eeit168.helpdesk.dao;

import java.util.List;

import org.json.JSONObject;

import tw.com.eeit168.helpdesk.model.HelpDeskBean;
import tw.com.eeit168.member.model.MemberProfileBean;

public interface HelpDeskInterFace {

	public abstract HelpDeskBean insert(HelpDeskBean bean);

	public abstract List<HelpDeskBean> selectTicket(JSONObject obj);

	public abstract HelpDeskBean selectTicketById(Integer helpdesk_id);

	public abstract HelpDeskBean modifyHelpdeskStatus(HelpDeskBean bean);

	public abstract List<MemberProfileBean> selectCustomerUser(JSONObject obj);

	public long ticketTotalCount(JSONObject obj);
	
}
