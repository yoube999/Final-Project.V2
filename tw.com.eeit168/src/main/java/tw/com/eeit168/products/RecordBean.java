package tw.com.eeit168.products;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity //是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "record")
public class RecordBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 這個用來標記此欄位是自動產生
	@Column(name = "record_id")
	private Integer recordId;

	@JoinColumn(name = "member_profile_id")
	private Integer memberProfileId;

	@Column(name = "created_at", insertable = false)
	private java.sql.Date createdAt;

	@Column(name = "record_status", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String recordStatus;

	@Column(name = "return_title", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String returnTitle;

	@Column(name = "return_description", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String returnDescription;

	@Column(name = "return_date")
	private java.sql.Date returnDate;
	
	@Column(name = "total_price")
	private Integer totalPrice;

	
	@Override
	public String toString() {
		return "RecordBean [recordId=" + recordId + ", memberProfileId=" + memberProfileId + ", createdAt=" + createdAt
				+ ", recordStatus=" + recordStatus + ", returnTitle=" + returnTitle + ", returnDescription="
				+ returnDescription + ", returnDate=" + returnDate + ", totalPrice=" + totalPrice + "]";
	}

	
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public Integer getMemberProfileId() {
		return memberProfileId;
	}
	public void setMemberProfileId(Integer memberProfileId) {
		this.memberProfileId = memberProfileId;
	}
	public java.sql.Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(java.sql.Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getReturnTitle() {
		return returnTitle;
	}
	public void setReturnTitle(String returnTitle) {
		this.returnTitle = returnTitle;
	}
	public String getReturnDescription() {
		return returnDescription;
	}
	public void setReturnDescription(String returnDescription) {
		this.returnDescription = returnDescription;
	}
	public java.sql.Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(java.sql.Date returnDate) {
		this.returnDate = returnDate;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

}
