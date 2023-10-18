package tw.com.eeit168.products;

import java.util.List;

public interface RecordRepositoryDAO {

	public List<RecordBean> findByMemberId(Integer id);
	
}
