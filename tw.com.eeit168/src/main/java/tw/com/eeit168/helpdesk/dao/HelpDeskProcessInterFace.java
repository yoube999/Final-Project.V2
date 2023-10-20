package tw.com.eeit168.helpdesk.dao;

import java.util.List;

import org.json.JSONObject;

import tw.com.eeit168.helpdesk.model.HelpDeskProcessBean;
import tw.com.eeit168.helpdesk.model.HelpDeskProcessWithNameBean;

public interface HelpDeskProcessInterFace {

	public abstract HelpDeskProcessBean insertComment(HelpDeskProcessBean bean);

	public abstract List<HelpDeskProcessWithNameBean> selectTicketCommentById(JSONObject obj);

}
