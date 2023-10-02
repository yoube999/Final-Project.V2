package tw.com.eeit168.member.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import tw.com.eeit168.member.dao.CreditCardInterFace;
import tw.com.eeit168.member.model.CreditCardBean;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardInterFace creditCardInterFace;

 // 新增信用卡
    public void insertCreditCardFromJson(JsonNode jsonNode) throws ParseException {
        int memberProfileId = jsonNode.get("member_profile_id").asInt();
        String cardName = jsonNode.get("card_name").asText();
        String cardType = jsonNode.get("card_type").asText();
        String cardNumber = jsonNode.get("card_number").asText();
        String expirationDateStr = jsonNode.get("expiration_date").asText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date expirationDate = sdf.parse(expirationDateStr);
        int securityCode = jsonNode.get("security_code").asInt();

        CreditCardBean creditCard = new CreditCardBean();
        creditCard.setMemberProfileId(memberProfileId);
        creditCard.setCardName(cardName);
        creditCard.setCardType(cardType);
        creditCard.setCardNumber(cardNumber);
        creditCard.setExpirationDate(expirationDate);
        creditCard.setSecurityCode(securityCode);

        creditCardInterFace.insert(creditCard);
    }
    // 删除信用卡
    public void deleteCreditCardFromJson(JsonNode jsonNode) {
        int creditCardId = jsonNode.get("credit_card_id").asInt();
        creditCardInterFace.delete(creditCardId);
    }



    // 通過會員配置 ID 查詢信用卡列表
    public List<CreditCardBean> findCreditCardsByMemberProfileIdFromJson(JsonNode jsonNode) {
        int memberProfileId = jsonNode.get("member_profile_id").asInt();
        return creditCardInterFace.selectCreditCardsByMemberProfileId(memberProfileId);
    }
}