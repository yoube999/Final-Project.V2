package tw.com.eeit168.member.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.member.model.CreditCardBean;

@Repository
public class CreditCardDAO implements CreditCardInterFace {

    @PersistenceContext
    private Session session;

    public Session getSession() {
        return session;
    }
    
    @Transactional
    @Override
    public void insert(CreditCardBean creditCard) {
        session.save(creditCard);
    }
    
    @Transactional
    @Override
    public void delete(int creditCardId) {
        CreditCardBean creditCard = session.get(CreditCardBean.class, creditCardId);
        if (creditCard != null) {
            session.delete(creditCard);
        }
    }

    @Transactional
    @Override
    public CreditCardBean selectCreditCardById(int creditCardId) {
        return session.get(CreditCardBean.class, creditCardId);
    }

    @Transactional
    @Override
    public List<CreditCardBean> selectCreditCardsByMemberProfileId(int memberProfileId) {
        String hql = "FROM CreditCardBean WHERE member_profile_id = :memberProfileId";
        Query<CreditCardBean> query = session.createQuery(hql);
        query.setParameter("memberProfileId", memberProfileId);
        return query.list();
    }
}
