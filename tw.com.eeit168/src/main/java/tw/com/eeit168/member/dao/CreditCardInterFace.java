package tw.com.eeit168.member.dao;

import java.util.List;

import tw.com.eeit168.member.model.CreditCardBean;

public interface CreditCardInterFace {

    // 插入信用卡
    void insert(CreditCardBean creditCard);

    // 删除信用卡
    void delete(int creditCardId);

    // 通过信用卡 ID 查询信用卡
    CreditCardBean selectCreditCardById(int creditCardId);
    
    List<CreditCardBean> selectCreditCardsByMemberProfileId(int memberProfileId);
}

