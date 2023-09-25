package tw.com.eeit168.member.service;

import java.text.SimpleDateFormat;
import java.util.Date; // 导入日期类

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;

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
    
    public void registerMember(JsonNode jsonNode) {
        try {
            String user_account = jsonNode.get("user_account").asText();

            // 检查是否存在重复的用户帐户
            if (isUserAccountDuplicate(user_account)) {
                throw new RuntimeException("使用者帳戶已存在"); // 抛出异常
            }

            // 其余的注册逻辑
            String user_password = jsonNode.get("user_password").asText();
            String username = jsonNode.get("username").asText();
            String gender = jsonNode.get("gender").asText();
            String phone_number = jsonNode.get("phone_number").asText();
            String birthday = jsonNode.get("birthday").asText();

            // 解析日期字符串为 Date 对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthdayDate = sdf.parse(birthday);

            MemberProfileBean newMember = new MemberProfileBean();
            newMember.setUser_account(user_account);
            newMember.setUser_password(user_password);
            newMember.setUsername(username);
            newMember.setGender(gender);
            newMember.setPhone_number(phone_number);
            newMember.setBirthday(birthdayDate);

            Date registrationDate = new Date();
            newMember.setRegistration_date(registrationDate);
            newMember.setAccount_status("default");
            newMember.setMember_level(1);
            newMember.setVerification_code(null);
            newMember.setAttempts(1);

            memberProfileInterFace.insert(newMember);
        } catch (Exception e) {
            // 处理异常
            throw new RuntimeException("註冊失敗：" + e.getMessage());
        }
    }
        // 检查用户帐户是否重复的方法
        private boolean isUserAccountDuplicate(String user_account) {
            MemberProfileBean existingMember = memberProfileInterFace.select(user_account);
            return existingMember != null;
        }   
}