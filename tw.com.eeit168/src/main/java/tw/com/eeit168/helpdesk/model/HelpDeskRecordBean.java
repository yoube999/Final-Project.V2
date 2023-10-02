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

	@Override
	public String toString() {
		return "HelpDeskRecordBean [record_id=" + record_id + ", username=" + username + ", return_title="
				+ return_title + "]";
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

	

}
