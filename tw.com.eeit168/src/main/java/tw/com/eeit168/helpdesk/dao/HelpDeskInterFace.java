package tw.com.eeit168.helpdesk.dao;

import java.util.List;

import org.json.JSONObject;

import tw.com.eeit168.helpdesk.model.HelpDeskBean;

public interface HelpDeskInterFace {

	HelpDeskBean insert(HelpDeskBean bean);

	List<HelpDeskBean> selectTicket(JSONObject obj);

}
