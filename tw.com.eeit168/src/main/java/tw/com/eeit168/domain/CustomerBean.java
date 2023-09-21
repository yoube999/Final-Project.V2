package tw.com.eeit168.domain;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="customer")
public class CustomerBean {
	
	@Id
	@Column(name="memberProfileId")
	private int memberProfileId;
	
	@Column(name="userAccount")
    private String userAccount;
	
	@Column(name="userPassword")
    private String userPassword;
	
	@Column(name="username")
    private String username;
	
	@Column(name="birthday")
    private java.util.Date birthday;
	
	@Column(name="gender")
    private String gender;
	
	@Column(name="phoneNumber")
    private String phoneNumber;
	
	@Column(name="registrationDate")
    private java.util.Date registrationDate;
	
	@Column(name="accountStatus")
    private String accountStatus;
	
	@Column(name="memberLevel")
    private String memberLevel;
	
	@Column(name="verificationCode")
    private String verificationCode;
	
	@Column(name="attempts")
    private int attempts;
	
    @Override
    public String toString() {
    	return "CustomerBean [memberProfileId=" + memberProfileId + ", userAccount=" + userAccount + ", userPassword="
    			+ userPassword + ", username=" + username + ", birthday=" + birthday + ", gender=" + gender
    			+ ", phoneNumber=" + phoneNumber + ", registrationDate=" + registrationDate + ", accountStatus="
    			+ accountStatus + ", memberLevel=" + memberLevel + ", verificationCode=" + verificationCode
    			+ ", attempts=" + attempts + "]";
    }
    
	
    public int getMemberProfileId() {
		return memberProfileId;
	}
	public void setMemberProfileId(int memberProfileId) {
		this.memberProfileId = memberProfileId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public java.util.Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public int getAttempts() {
		return attempts;
	}
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

}
