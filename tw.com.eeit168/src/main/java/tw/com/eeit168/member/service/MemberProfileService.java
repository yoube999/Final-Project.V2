package tw.com.eeit168.member.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.mail.internet.MimeMessage;
import tw.com.eeit168.member.dao.MemberProfileInterFace;
import tw.com.eeit168.member.model.MemberProfileBean;

@Service
public class MemberProfileService {

    private MemberProfileInterFace memberProfileInterFace;
    private JavaMailSender javaMailSender;

    @Autowired
    public MemberProfileService(MemberProfileInterFace memberProfileInterFace, JavaMailSender javaMailSender) {
        this.memberProfileInterFace = memberProfileInterFace;
        this.javaMailSender = javaMailSender;
    }

    @Transactional
    public boolean login(String user_account, String user_password) {
        MemberProfileBean bean = memberProfileInterFace.select(user_account);
        if (bean != null && bean.getUser_password().equals(user_password)) {
            return true; // 登入成功
        }
        return false; // 登入失敗
    }

    
    //註冊會員
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

            // 在這裡生成驗證碼
            String verificationCode = generateVerificationCode();

            // 存儲驗證碼到資料庫
            newMember.setVerification_code(verificationCode);

            newMember.setAttempts(1);

            memberProfileInterFace.insert(newMember);
        } catch (Exception e) {
            // 处理异常
            throw new RuntimeException("註冊失敗：" + e.getMessage());
        }
    }

    // 檢查帳戶是否正確
    private boolean isUserAccountDuplicate(String user_account) {
        MemberProfileBean existingMember = memberProfileInterFace.select(user_account);
        return existingMember != null;
    }

    // 隨機產生驗證碼 給sendVerificationCode用
    private String generateVerificationCode() {
        int codeLength = 6; // 密碼長度
        String characters = "0123456789"; // 密碼組合

        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < codeLength; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

    

 // 寄送驗證碼+時效性
    public void sendVerificationCode(String user_account) {
        String verificationCode = generateVerificationCode(); // 生成驗證碼
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(user_account);
            helper.setSubject("Verification Code");
            helper.setText("Your verification code is: " + verificationCode);

            // 設定驗證碼的過期時間為2分鐘後
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MINUTE, 1); // 添加2分鐘
            Date verificationCodeTimestamp = calendar.getTime();

            // 存儲驗證碼和過期時間到資料庫
            MemberProfileBean existingMember = memberProfileInterFace.select(user_account);
            if (existingMember != null) {
                existingMember.setVerification_code(verificationCode);
                existingMember.setVerification_code_timestamp(verificationCodeTimestamp);
                memberProfileInterFace.update(existingMember);
            }

            javaMailSender.send(message);
            System.out.println("已發送驗證碼郵件至：" + user_account); // 輸出郵件地址到控制台

        } catch (Exception e) {
            // 處理異常
        	throw new RuntimeException("發送驗證碼郵件失敗：" + e.getMessage());
        }
    }


    
  //確認驗證碼+改密碼
    public void changePasswordWithVerificationCode(String user_account, String verification_code, String user_password) {
        try {
            MemberProfileBean existingMember = memberProfileInterFace.select(user_account);

            if (existingMember != null) {
                String savedCode = existingMember.getVerification_code();
                Date codeTimestamp = existingMember.getVerification_code_timestamp();
                Date now = new Date();

                //判斷驗證碼過期codeTimestamp
                if (savedCode != null && verification_code.equals(savedCode) && now.before(codeTimestamp)) {
                    existingMember.setUser_password(user_password);
                    existingMember.setVerification_code(null);
                    memberProfileInterFace.update(existingMember);

                    if (existingMember.getUser_password().equals(user_password)) {
                        System.out.println("密码已成功更改！");
                    } else {
                        throw new RuntimeException("密码未成功更改");
                    }
                } else {
                    throw new RuntimeException("验证码错误或已过期");
                }
            } else {
                throw new RuntimeException("找不到要更改密码的会员");
            }
        } catch (RuntimeException e) {
            System.out.println("异常消息：" + e.getMessage());
            throw e;
        }
    }

    }

 


