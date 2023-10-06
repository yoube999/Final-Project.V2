package tw.com.eeit168.comment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "item_type")
public class ItemTypeBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //這個用來標記此欄位是自動產生
	@Column(name = "item_type_id")
	private Integer itemTypeId;
	
	@Column(name = "item_type_name", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String itemTypeName;

	
	@Override
	public String toString() {
		return "itemTypeBean [itemTypeId=" + itemTypeId + ", itemTypeName=" + itemTypeName + "]";
	}

	
	public Integer getItemTypeId() {
		return itemTypeId;
	}
	public void setItemTypeId(Integer itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	public String getItemTypeName() {
		return itemTypeName;
	}
	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}
	
}
