package tw.com.eeit168.member.service;

import java.util.Date; // 导入日期类

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.member.dao.MemberProfileInterFace;
import tw.com.eeit168.member.model.MemberProfileBean;

@Service
public class MemberProfileService {

    private MemberProfileInterFace memberProfileInterFace;

    @Autowired
    public MemberProfileService(MemberProfileInterFace memberProfileInterFace) {
        this.memberProfileInterFace = memberProfileInterFace;
    }

    @Transactional
    public boolean login(String user_account, String user_password) {
        MemberProfileBean bean = memberProfileInterFace.select(user_account);
        if (bean != null && bean.getUser_password().equals(user_password)) {
            return true; // 登入成功
        }
        return false; // 登入失敗
    }
    
    public void registerMember(String user_account, String user_password, String username, String gender, String phone_number, Date birthday) {
        MemberProfileBean newMember = new MemberProfileBean();
        newMember.setUser_account(user_account);
        newMember.setUser_password(user_password);
        newMember.setUsername(username);
        newMember.setGender(gender);
        newMember.setPhone_number(phone_number);
        newMember.setBirthday(birthday);

        Date registrationDate = new Date();
        newMember.setRegistration_date(registrationDate);

        newMember.setAccount_status(null);
        newMember.setMember_level(0);
        newMember.setVerification_code(null);
        newMember.setAttempts(0);

        memberProfileInterFace.insert(newMember);
    }

}
