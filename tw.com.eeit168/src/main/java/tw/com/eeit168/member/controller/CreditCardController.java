package tw.com.eeit168.member.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import tw.com.eeit168.member.model.CreditCardBean;
import tw.com.eeit168.member.service.CreditCardService;

@RestController
@RequestMapping("/credit-cards")
@CrossOrigin
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping("/member/insert-credit-card/{member_profile_id}")
    public void insertCreditCard(@RequestBody JsonNode jsonNode, @PathVariable("member_profile_id") int memberProfileId) throws ParseException {
        // memberProfileId 在这里已经是整数类型，不需要手动转换
        creditCardService.insertCreditCardFromJson(jsonNode, memberProfileId);
    }

    @DeleteMapping("/member/{credit_card_id}")
    public ResponseEntity<String> deleteCreditCard(@PathVariable("credit_card_id") int cardNumber) {
        creditCardService.deleteCreditCardByCardNumber(cardNumber);
        return new ResponseEntity<>("信用卡已成功刪除", HttpStatus.OK);
    }




    @PostMapping("/member/cards")
    public List<CreditCardBean> findCreditCardsByMemberProfileId(@RequestBody Map<String, Integer> requestBody) {
        int member_profile_id = requestBody.get("member_profile_id");
        return creditCardService.findCreditCardsByMemberProfileId(member_profile_id);
    }
}
