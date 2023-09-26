package tw.com.eeit168.member.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.eeit168.member.model.CreditCardBean;

@SpringBootTest
public class CreditCardTest {

    @Autowired
    private CreditCardInterFace creditCardInterFace;



//    @Test
    public void testInsertCreditCard() throws ParseException {
        // 创建一个 CreditCardBean 对象并设置相关属性
        CreditCardBean creditCard = new CreditCardBean();
        // 假设信用卡属于会员的 member_profile_id 为 1
        creditCard.setMemberProfileId(1);        
        // 设置信用卡的名称
        creditCard.setCardName("Visa");       
        // 设置信用卡类型
        creditCard.setCardType("Credit");       
        // 设置信用卡号
        creditCard.setCardNumber("1234-5678-9012-3456");        
        // 设置信用卡过期日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date expirationDate = sdf.parse("2025-12-31");
        creditCard.setExpirationDate(expirationDate);
        
        // 设置安全码
        creditCard.setSecurityCode(123);
        
        // 执行插入操作
        creditCardInterFace.insert(creditCard);
        
        // 使用断言确保插入成功并且 creditCardId 不为 null
        assertNotNull(creditCard.getCreditCardId());
    }


//    @Test
    public void testDeleteCreditCard() {
        int creditCardIdToDelete = 4; // 替换为要删除的信用卡的实际 ID
        creditCardInterFace.delete(creditCardIdToDelete);

        // 检查信用卡是否已成功删除
        CreditCardBean deletedCreditCard = creditCardInterFace.selectCreditCardById(creditCardIdToDelete);
        assertNull(deletedCreditCard);
    }

    @Test
    public void testSelectCreditCardsByMemberProfileId() {
        int memberProfileId = 1; // 替换为实际的会员配置 ID

        List<CreditCardBean> creditCards = creditCardInterFace.selectCreditCardsByMemberProfileId(memberProfileId);

        assertNotNull(creditCards);
        
        // 遍历每张信用卡并检查它们的属性
        for (CreditCardBean creditCard : creditCards) {
            System.out.println("Credit Card ID: " + creditCard.getCreditCardId());
            System.out.println("Card Name: " + creditCard.getCardName());
            System.out.println("Card Type: " + creditCard.getCardType());
            System.out.println("Card Number: " + creditCard.getCardNumber());
            System.out.println("Expiration Date: " + creditCard.getExpirationDate());
            System.out.println("Security Code: " + creditCard.getSecurityCode());
            // 其他属性...
        }
    }

}
