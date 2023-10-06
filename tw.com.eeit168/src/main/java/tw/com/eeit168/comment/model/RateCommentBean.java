package tw.com.eeit168.comment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "rate_comment")
public class RateCommentBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //這個用來標記此欄位是自動產生
	@Column(name = "rate_comment_id")
	private Integer rateCommentId;
	
	@Column(name = "item_type_id")
	private Integer itemTypeId;
	
	@Column(name = "member_profile_id")
	private Integer memberProfileId;
	
	@Column(name = "item_id")
	private Integer itemId;
	
	@Column(name = "rate", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private double rate;
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "create_at", insertable = false)
	private java.util.Date createAt;

	
	@Override
	public String toString() {
		return "RateCommentBean [rateCommentId=" + rateCommentId + ", itemTypeId=" + itemTypeId + ", memberProfileId="
				+ memberProfileId + ", itemId=" + itemId + ", rate=" + rate + ", comments=" + comments + ", createAt="
				+ createAt + "]";
	}

	
	public Integer getRateCommentId() {
		return rateCommentId;
	}
	public void setRateCommentId(Integer rateCommentId) {
		this.rateCommentId = rateCommentId;
	}
	public Integer getItemTypeId() {
		return itemTypeId;
	}
	public void setItemTypeId(Integer itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	public Integer getMemberProfileId() {
		return memberProfileId;
	}
	public void setMemberProfileId(Integer memberProfileId) {
		this.memberProfileId = memberProfileId;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public java.util.Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(java.util.Date createAt) {
		this.createAt = createAt;
	}
	
}
