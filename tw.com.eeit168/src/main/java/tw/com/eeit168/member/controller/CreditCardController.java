package tw.com.eeit168.member.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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



    @GetMapping("/member/cards")
    public List<CreditCardBean> findCreditCardsByMemberProfileId(@RequestBody JsonNode jsonNode) {
        return creditCardService.findCreditCardsByMemberProfileIdFromJson(jsonNode);
    }
}
