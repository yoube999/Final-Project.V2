package tw.com.eeit168.helpdesk.dao;

import java.util.List;

import org.json.JSONObject;

import tw.com.eeit168.helpdesk.model.HelpDeskRecordBean;

public interface HelpDeskRecordInterFace {
	
	public abstract List<HelpDeskRecordBean> selectRecordByStatus(JSONObject obj);

}
