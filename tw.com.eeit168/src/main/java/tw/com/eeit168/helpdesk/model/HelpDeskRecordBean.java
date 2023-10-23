package tw.com.eeit168.helpdesk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "select_retrun_record")
public class HelpDeskRecordBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 這個用來標記此欄位是自動產生
	@Column(name = "record_id")
	private Integer record_id;

	@Column(name = "username", columnDefinition = "nvarchar")
	private String username;

	@Column(name = "return_title", columnDefinition = "nvarchar")
	private String return_title;

	@Column(name = "member_profile_id")
	private Integer member_profile_id;

	@Column(name = "return_date")
	private java.util.Date return_date;

	@Column(name = "record_status", columnDefinition = "nvarchar")
	private String record_status;

	@Column(name = "return_description", columnDefinition = "nvarchar")
	private String returnDescription;

	@Column(name = "user_account")
	private String user_account;

	@Override
	public String toString() {
		return "HelpDeskRecordBean [record_id=" + record_id + ", username=" + username + ", return_title="
				+ return_title + ", member_profile_id=" + member_profile_id + ", return_date=" + return_date
				+ ", record_status=" + record_status + ", returnDescription=" + returnDescription + ", user_account="
				+ user_account + "]";
	}

	public Integer getRecord_id() {
		return record_id;
	}

	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReturn_title() {
		return return_title;
	}

	public void setReturn_title(String return_title) {
		this.return_title = return_title;
	}

	public Integer getMember_profile_id() {
		return member_profile_id;
	}

	public void setMember_profile_id(Integer member_profile_id) {
		this.member_profile_id = member_profile_id;
	}

	public java.util.Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(java.util.Date return_date) {
		this.return_date = return_date;
	}

	public String getRecord_status() {
		return record_status;
	}

	public void setRecord_status(String record_status) {
		this.record_status = record_status;
	}

	public String getReturnDescription() {
		return returnDescription;
	}

	public void setReturnDescription(String returnDescription) {
		this.returnDescription = returnDescription;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	
}
