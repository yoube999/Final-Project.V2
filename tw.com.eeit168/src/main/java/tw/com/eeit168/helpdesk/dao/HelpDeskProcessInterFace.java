package tw.com.eeit168.helpdesk.dao;

import java.util.List;

import org.json.JSONObject;

import tw.com.eeit168.helpdesk.model.HelpDeskProcessBean;

public interface HelpDeskProcessInterFace {

	public abstract HelpDeskProcessBean insertComment(HelpDeskProcessBean bean);

	public abstract List<HelpDeskProcessBean> selectTicketCommentById(JSONObject obj);
}
