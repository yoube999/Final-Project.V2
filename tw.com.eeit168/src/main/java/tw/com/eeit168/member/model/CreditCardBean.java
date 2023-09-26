package tw.com.eeit168.member.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "credit_card")
public class CreditCardBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_card_id")
    private int credit_card_id;

    @Column(name = "member_profile_id")
    private int member_profile_id;

    @Column(name = "card_name", columnDefinition = "nvarchar(20)")
    private String card_name;

    @Column(name = "card_type", columnDefinition = "varchar(20)")
    private String card_type;

    @Column(name = "card_number", columnDefinition = "varchar(20)")
    private String card_number;

    @Column(name = "expiration_date")
    private Date expiration_date;

    @Column(name = "security_code")
    private int security_code;

    public int getCreditCardId() {
        return credit_card_id;
    }

    public void setCreditCardId(int credit_card_id) {
        this.credit_card_id = credit_card_id;
    }

    public int getMemberProfileId() {
        return member_profile_id;
    }

    public void setMemberProfileId(int member_profile_id) {
        this.member_profile_id = member_profile_id;
    }

    public String getCardName() {
        return card_name;
    }

    public void setCardName(String card_name) {
        this.card_name = card_name;
    }

    public String getCardType() {
        return card_type;
    }

    public void setCardType(String card_type) {
        this.card_type = card_type;
    }

    public String getCardNumber() {
        return card_number;
    }

    public void setCardNumber(String card_number) {
        this.card_number = card_number;
    }

    public Date getExpirationDate() {
        return expiration_date;
    }

    public void setExpirationDate(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public int getSecurityCode() {
        return security_code;
    }

    public void setSecurityCode(int security_code) {
        this.security_code = security_code;
    }
    
    @Override
    public String toString() {
        return "CreditCardBean [creditCardId=" + credit_card_id + ", memberProfileId=" + member_profile_id
                + ", cardName=" + card_name + ", cardType=" + card_type + ", cardNumber=" + card_number
                + ", expirationDate=" + expiration_date + ", securityCode=" + security_code + "]";
    }
}
