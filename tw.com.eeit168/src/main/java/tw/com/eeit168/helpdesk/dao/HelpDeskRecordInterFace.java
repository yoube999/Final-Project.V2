package tw.com.eeit168.helpdesk.dao;

import java.util.List;

import org.json.JSONObject;

import tw.com.eeit168.helpdesk.model.HelpDeskRecordBean;
import tw.com.eeit168.member.model.MemberProfileBean;
import tw.com.eeit168.products.RecordBean;

public interface HelpDeskRecordInterFace {
	
	public abstract List<HelpDeskRecordBean> selectRecordByStatus(JSONObject obj);
	
	public abstract MemberProfileBean selectUserById(Integer member_profile_id);
	
	public abstract RecordBean selectRecordById(Integer record_id);

}
