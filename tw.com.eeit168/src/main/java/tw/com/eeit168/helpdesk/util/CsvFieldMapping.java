package tw.com.eeit168.helpdesk.util;

import java.util.List;

// 用於表示 CSV 檔案中的字段映射
public class CsvFieldMapping {
	// 這是 CsvFieldMapping 類別的私有成員變數，它是一個 List 類型，用於存儲 CSV 檔案中的字段名稱。
	private List<String> fieldNames;

	//這個建構函式接受一個 List<String> 參數，該參數包含 CSV 檔案中的字段名稱。
	//建構函式將這些字段名稱初始化並設置到 fieldNames 成員變數中。
    public CsvFieldMapping(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    // 用於根據索引值（index）獲取對應的字段名稱。它接受一個整數索引值作為參數，並檢查該索引值是否在有效範圍內（即大於等於 0 且小於字段名稱列表的大小）
    // 如果索引值有效，則返回對應的字段名稱；否則返回 null。
    public String getFieldName(int index) {
        if (index >= 0 && index < fieldNames.size()) {
            return fieldNames.get(index);
        }
        return null;
    }
    // 用於獲取 CSV 檔案中的字段數量。它簡單地返回 fieldNames 列表的大小，即字段的總數。
    public int getFieldCount() {
        return fieldNames.size();
    }
}
