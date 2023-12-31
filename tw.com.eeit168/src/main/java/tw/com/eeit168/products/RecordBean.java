package tw.com.eeit168.products;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "record")
public class RecordBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 這個用來標記此欄位是自動產生
	@Column(name = "record_id")
	private Integer record_id;

	@JoinColumn(name = "member_profile_id")
	private Integer member_profile_id;

	@Column(name = "created_at")
	private java.util.Date created_at;

	@Column(name = "record_status", columnDefinition = "nvarchar")
	private String record_status;

	@Column(name = "return_title", columnDefinition = "nvarchar")
	private String return_title;

	@Column(name = "return_description", columnDefinition = "nvarchar")
	private String return_description;

	@Column(name = "return_date")
	private java.util.Date return_date;

	@Override
	public String toString() {
		return "RecordBean [record_id=" + record_id + ", member_profile_id=" + member_profile_id + ", created_at="
				+ created_at + ", record_status=" + record_status + ", return_title=" + return_title
				+ ", return_description=" + return_description + ", return_date=" + return_date + "]";
	}

	public Integer getRecord_id() {
		return record_id;
	}

	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}

	public Integer getMember_profile_id() {
		return member_profile_id;
	}

	public void setMember_profile_id(Integer member_profile_id) {
		this.member_profile_id = member_profile_id;
	}

	public java.util.Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(java.util.Date created_at) {
		this.created_at = created_at;
	}

	public String getRecord_status() {
		return record_status;
	}

	public void setRecord_status(String record_status) {
		this.record_status = record_status;
	}

	public String getReturn_title() {
		return return_title;
	}

	public void setReturn_title(String return_title) {
		this.return_title = return_title;
	}

	public String getReturn_description() {
		return return_description;
	}

	public void setReturn_description(String return_description) {
		this.return_description = return_description;
	}

	public java.util.Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(java.util.Date return_date) {
		this.return_date = return_date;
	}

}
