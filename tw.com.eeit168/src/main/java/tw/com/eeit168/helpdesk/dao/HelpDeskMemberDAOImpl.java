package tw.com.eeit168.helpdesk.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.member.model.MemberProfileBean;

public class HelpDeskMemberDAOImpl implements HelpDeskMemberDAO {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	/**
	 * 前端點擊編輯客服人員頁面時，顯示客服人員清單
	 * 
	 * @param memberLevel: 固定查詢條件為99和100
	 * 
	 * @return List<MemberProfileBean>
	 */
	@Override
	public List<MemberProfileBean> selectCustomerService(List<Integer> levels, int start, int row) {
		if (levels != null) {
			Query<MemberProfileBean> query = session
					.createQuery("FROM MemberProfileBean WHERE member_level IN (:levels)", MemberProfileBean.class);
			query.setParameterList("levels", levels);

			// 添加分頁設定
			if (row > 0) {
				query.setFirstResult(start * row); // 設置起始行
				query.setMaxResults(row); // 設置每頁顯示的記錄數
			}
			return query.list();
		}
		return null;

	}

	/**
	 * 前端點擊編輯客服人員頁面時，顯示客服人員總數量
	 * 
	 * @param memberLevel: 固定查詢條件為99和100
	 * 
	 * @return long 數量
	 */
	@Override
	public long memberTotalCount(List<Integer> levels) {
		if (levels != null) {
			Query<Long> query = session
					.createQuery("SELECT COUNT(*) FROM MemberProfileBean WHERE member_level IN (:levels)", Long.class);
			query.setParameterList("levels", levels);
			return query.uniqueResult();
		}
		return 0;
	}

}
