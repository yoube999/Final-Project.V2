package tw.com.eeit168.member.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/member")
    public void insertCreditCard(@RequestBody JsonNode jsonNode) throws ParseException {
        creditCardService.insertCreditCardFromJson(jsonNode);
    }

    @DeleteMapping("/member")
    public void deleteCreditCard(@RequestBody JsonNode jsonNode) {
        creditCardService.deleteCreditCardFromJson(jsonNode);
    }



    @GetMapping("/member/cards/{member_profile_id}")
    public List<CreditCardBean> findCreditCardsByMemberProfileId(@PathVariable("member_profile_id") int member_profile_id) {
        // 在方法中使用 memberProfileId 参数
        // 调用服务方法并传递 memberProfileId
        return creditCardService.findCreditCardsByMemberProfileId(member_profile_id);
    }
}
