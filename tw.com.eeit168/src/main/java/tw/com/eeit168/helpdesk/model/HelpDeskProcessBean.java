package tw.com.eeit168.helpdesk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "helpdesk_process")
public class HelpDeskProcessBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "helpdesk_process_id")
	private Integer helpdesk_process_id;

	@Column(name = "helpdesk_id")
	private Integer helpdesk_id;

	@Column(name = "process_description")
	private String process_description;

	@Column(name = "attachment")
	private String attachment;

	@Column(name = "member_profile_id")
	private Integer member_profile_id;

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

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Integer getMember_profile_id() {
		return member_profile_id;
	}

	public void setMember_profile_id(Integer member_profile_id) {
		this.member_profile_id = member_profile_id;
	}

	@Override
	public String toString() {
		return "HelpDeskProcessBean [helpdesk_process_id=" + helpdesk_process_id + ", helpdesk_id=" + helpdesk_id
				+ ", process_description=" + process_description + ", attachment=" + attachment + ", member_profile_id="
				+ member_profile_id + "]";
	}

}
