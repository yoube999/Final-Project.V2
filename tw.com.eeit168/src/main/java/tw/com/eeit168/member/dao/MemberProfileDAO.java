package tw.com.eeit168.member.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.member.model.MemberProfileBean;

	@Repository
	public  class MemberProfileDAO implements MemberProfileInterFace {

	    @PersistenceContext
	    private Session session;

	    public Session getSession() {
	        return session;
	    }

	    // 查詢帳號
	    @Override
	    public MemberProfileBean select(String user_account) {
	    	   Session session = getSession();
	           String hql = "FROM MemberProfileBean WHERE user_account = :account";
	           Query query = session.createQuery(hql);
	           query.setParameter("account", user_account);

	           return (MemberProfileBean) query.uniqueResult();
	    }

	    @Transactional
	    @Override
	    public void insert(MemberProfileBean memberProfileBean) {
	        if (memberProfileBean != null) {
	            this.getSession().persist(memberProfileBean);
	        }
	    }
	    
	    @Transactional
	    @Override
	    public void update(MemberProfileBean memberProfileBean) {
	        if (memberProfileBean != null) {
	            this.getSession().update(memberProfileBean);
	        }
	    }

		@Override
		public MemberProfileBean selectByProfileId(int member_profile_id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MemberProfileBean select(int member_profile_id) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	

	
	

