package tw.com.eeit168.helpdesk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "helpdesk")
public class HelpDeskBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "helpdesk_id")
	private Integer helpdesk_id;

	@Column(name = "customer_name", columnDefinition = "nvarchar")
	private String customer_name;

	@Column(name = "record_id")
	private Integer record_id;

	@Column(name = "subject_line", columnDefinition = "nvarchar")
	private String subject_line;

	@Column(name = "descriptions", columnDefinition = "nvarchar")
	private String descriptions;

	@Column(name = "contact_number")
	private String contact_number;

	@Column(name = "email")
	private String email;

	@Column(name = "way_to_contact", columnDefinition = "nvarchar")
	private String way_to_contact;

	@Column(name = "attachment")
	private String attachment;

	@Column(name = "member_profile_id")
	private Integer member_profile_id;

	@Column(name = "helpdesk_status", columnDefinition = "nvarchar")
	private String helpdesk_status;

	@Column(name = "createtime")
	private java.util.Date createtime;

	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "HelpDeskBean [helpdesk_id=" + helpdesk_id + ", customer_name=" + customer_name + ", record_id="
				+ record_id + ", subject_line=" + subject_line + ", descriptions=" + descriptions + ", contact_number="
				+ contact_number + ", email=" + email + ", way_to_contact=" + way_to_contact + ", attachment="
				+ attachment + ", member_profile_id=" + member_profile_id + ", helpdesk_status=" + helpdesk_status
				+ ", createtime=" + createtime + "]";
	}

	public Integer getHelpdesk_id() {
		return helpdesk_id;
	}

	public void setHelpdesk_id(Integer helpdesk_id) {
		this.helpdesk_id = helpdesk_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public Integer getRecord_id() {
		return record_id;
	}

	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}

	public String getSubject_line() {
		return subject_line;
	}

	public void setSubject_line(String subject_line) {
		this.subject_line = subject_line;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWay_to_contact() {
		return way_to_contact;
	}

	public void setWay_to_contact(String way_to_contact) {
		this.way_to_contact = way_to_contact;
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

	public String getHelpdesk_status() {
		return helpdesk_status;
	}

	public void setHelpdesk_status(String helpdesk_status) {
		this.helpdesk_status = helpdesk_status;
	}

}
