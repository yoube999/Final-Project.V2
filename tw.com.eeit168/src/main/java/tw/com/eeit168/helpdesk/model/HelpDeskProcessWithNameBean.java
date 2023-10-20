package tw.com.eeit168.helpdesk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "select_helpdesk_process_with_username")
public class HelpDeskProcessWithNameBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "helpdesk_process_id")
	private Integer helpdesk_process_id;

	@Column(name = "helpdesk_id")
	private Integer helpdesk_id;

	@Column(name = "process_description")
	private String process_description;

	@Column(name = "username", columnDefinition = "nvarchar")
	private String username;

	@Column(name = "createtime")
	private java.util.Date createtime;

	public Integer getHelpdesk_process_id() {
		return helpdesk_process_id;
	}

	public void setHelpdesk_process_id(Integer helpdesk_process_id) {
		this.helpdesk_process_id = helpdesk_process_id;
	}

	public Integer getHelpdesk_id() {
		return helpdesk_id;
	}

	public void setHelpdesk_id(Integer helpdesk_id) {
		this.helpdesk_id = helpdesk_id;
	}

	public String getProcess_description() {
		return process_description;
	}

	public void setProcess_description(String process_description) {
		this.process_description = process_description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "HelpDeskProcessWithNameBean [helpdesk_process_id=" + helpdesk_process_id + ", helpdesk_id="
				+ helpdesk_id + ", process_description=" + process_description + ", username=" + username
				+ ", createtime=" + createtime + "]";
	}

}
