package tw.com.eeit168.member.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "member_profile")
public class MemberProfileBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_profile_id")
	private Integer member_profile_id;

	@Column(name = "user_account")
	private String user_account;

	@Column(name = "user_password")
	private String user_password;

	@Column(name = "username", columnDefinition = "nvarchar")
	private String username;

	@Column(name = "birthday")
	private java.util.Date birthday;

	@Column(name = "gender", columnDefinition = "nvarchar")
	private String gender;

	@Column(name = "phone_number")
	private String phone_number;

	@Column(name = "registration_date")
	private java.util.Date registration_date;

	@Column(name = "account_status", columnDefinition = "nvarchar")
	private String account_status;

	@Column(name = "member_level")
	private int member_level;

	@Column(name = "verification_code")
	private String verification_code;

	@Column(name = "verification_code_timestamp")
	private Date verification_code_timestamp; // 新增時間戳屬性

	@Column(name = "attempts")
	private int attempts;

	@Override
	public String toString() {
		return "CustomerBean [memberProfileId=" + member_profile_id + ", user_account=" + user_account
				+ ", user_password=" + user_password + ", username=" + username + ", birthday=" + birthday + ", gender="
				+ gender + ", phone_number=" + phone_number + ", registration_date=" + registration_date
				+ ", account_status=" + account_status + ", member_level=" + member_level + ", verification_code="
				+ verification_code + ", attempts=" + attempts + "]";
	}

	public Integer getMemberProfileId() {
		return member_profile_id;
	}

	public void setMemberProfileId(Integer memberProfileId) {
		this.member_profile_id = memberProfileId;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public java.util.Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(java.util.Date registration_date) {
		this.registration_date = registration_date;
	}

	public String getAccount_status() {
		return account_status;
	}

	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}

	public int getMember_level() {
		return member_level;
	}

	public void setMember_level(int member_level) {
		this.member_level = member_level;
	}

	public String getVerification_code() {
		return verification_code;
	}

	public void setVerification_code(String verification_code) {
		this.verification_code = verification_code;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public Date getVerification_code_timestamp() {
		return verification_code_timestamp;
	}

	public void setVerification_code_timestamp(Date verification_code_timestamp) {
		this.verification_code_timestamp = verification_code_timestamp;
	}
}
