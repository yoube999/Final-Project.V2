package tw.com.eeit168.helpdesk.dao;

import java.util.List;

import org.json.JSONObject;

import tw.com.eeit168.helpdesk.model.HelpDeskBean;

public interface HelpDeskInterFace {

	public abstract HelpDeskBean insert(HelpDeskBean bean);

	public abstract List<HelpDeskBean> selectTicket(JSONObject obj);

	public abstract HelpDeskBean selectTicketById(Integer helpdesk_id);

}
