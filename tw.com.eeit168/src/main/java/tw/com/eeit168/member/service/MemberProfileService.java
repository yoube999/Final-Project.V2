package tw.com.eeit168.member.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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
@Transactional
public class MemberProfileService {

    private MemberProfileInterFace memberProfileInterFace;
    private JavaMailSender javaMailSender;



    @Autowired
    public MemberProfileService(MemberProfileInterFace memberProfileInterFace, JavaMailSender javaMailSender) {
        this.memberProfileInterFace = memberProfileInterFace;
        this.javaMailSender = javaMailSender;
    }

 
    public MemberProfileBean login(JsonNode jsonNode) {
        // 从JsonNode中获取用户名和密码
        String user_account = jsonNode.get("user_account").asText();
        String user_password = jsonNode.get("user_password").asText();

        MemberProfileBean bean = memberProfileInterFace.select(user_account);
        if (bean != null && bean.getUser_password().equals(user_password)) {
            return bean; // 返回完整的 MemberProfileBean 对象
        }
        return null; // 登录失败，返回 null
    }
    
    
    // 注册会员
    public void registerMember(JsonNode jsonNode) {
        try {
            String user_account = jsonNode.get("user_account").asText();

            // 检查是否存在重复的用户帐户
            if (isUserAccountDuplicate(user_account)) {
                throw new RuntimeException("使用者帳號已存在"); // 抛出异常
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

            // 在这里生成验证代码
            String verificationCode = generateVerificationCode();

            // 存储验证代码到数据库
            newMember.setVerification_code(verificationCode);

            newMember.setAttempts(1);

            memberProfileInterFace.insert(newMember);
        } catch (Exception e) {
            // 处理异常
            throw new RuntimeException("註冊失敗：" + e.getMessage());
        }
    }

    // 检查帐户是否正确
    private boolean isUserAccountDuplicate(String user_account) {
        MemberProfileBean existingMember = memberProfileInterFace.select(user_account);
        return existingMember != null;
    }

    // 随机生成验证代码 给sendVerificationCode用
    private String generateVerificationCode() {
        int codeLength = 6; // 密码长度
        String characters = "0123456789"; // 密码组合

        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < codeLength; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

    // 发送验证代码+时效性
    public void sendVerificationCode(String user_account) {
        String verificationCode = generateVerificationCode(); // 生成验证代码
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(user_account);
            helper.setSubject("Verification Code");
            helper.setText("Your verification code is: " + verificationCode);

            // 设置验证代码的过期时间为2分钟后
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(java.util.Calendar.MINUTE, 2); // 添加2分钟
            Date verificationCodeTimestamp = calendar.getTime();

            // 存储验证代码和过期时间到数据库
            MemberProfileBean existingMember = memberProfileInterFace.select(user_account);
            if (existingMember != null) {
                existingMember.setVerification_code(verificationCode);
                existingMember.setVerification_code_timestamp(verificationCodeTimestamp);
                memberProfileInterFace.update(existingMember);
            }

            javaMailSender.send(message);
            System.out.println("已發送驗證碼郵件至：" + user_account); // 输出邮件地址到控制台

        } catch (Exception e) {
            // 处理异常
            throw new RuntimeException("發送驗證碼郵件失敗：" + e.getMessage());
        }
    }

    // 确认验证代码+改密码
    public void changePasswordWithVerificationCode(String user_account, String verification_code, String user_password) {
        try {
            MemberProfileBean existingMember = memberProfileInterFace.select(user_account);

            if (existingMember != null) {
                String savedCode = existingMember.getVerification_code();
                Date codeTimestamp = existingMember.getVerification_code_timestamp();
                Date now = new Date();

                //判断验证代码过期codeTimestamp
                if (savedCode != null && verification_code.equals(savedCode) && now.before(codeTimestamp)) {
                    existingMember.setUser_password(user_password);
                    existingMember.setVerification_code(null);
                    memberProfileInterFace.update(existingMember);

                    if (existingMember.getUser_password().equals(user_password)) {
                        System.out.println("密碼已成功更改！");
                    } else {
                        throw new RuntimeException("密碼未成功更改");
                    }
                } else {
                    throw new RuntimeException("驗證碼錯誤或已過期");
                }
            } else {
                throw new RuntimeException("找不到要更改密碼的會員");
            }
        } catch (RuntimeException e) {
            System.out.println("異常消息：" + e.getMessage());
            throw e;
        }
    }
    
    
    public void updateMemberInfo(String user_account, JsonNode jsonNode) throws Exception {
        // 直接使用 memberProfileInterFace 查询会员记录
        MemberProfileBean existingMember = memberProfileInterFace.select(user_account);

        if (existingMember == null) {
            throw new Exception("找不到要更新的会员记录");
        }

        try {
            // 从请求 JSON 中获取字段值
            String user_password = jsonNode.get("user_password").asText();
            String username = jsonNode.get("username").asText();
            String gender = jsonNode.get("gender").asText();
            String phone_number = jsonNode.get("phone_number").asText();
            String birthday = jsonNode.get("birthday").asText();

            // 解析生日字符串为 Date 对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthdayDate = sdf.parse(birthday);

            // 更新会员信息
            existingMember.setUser_password(user_password);
            existingMember.setUsername(username);
            existingMember.setGender(gender);
            existingMember.setPhone_number(phone_number);
            existingMember.setBirthday(birthdayDate);
            
            
            memberProfileInterFace.update(existingMember);

            // 不需要调用保存方法，因为查询到的对象已经与数据库关联
        } catch (ParseException e) {
            // 处理 ParseException，您可以选择抛出自定义异常或以其他方式处理它
            e.printStackTrace(); // 这里只是简单的打印异常堆栈信息
        }
    }
}

    // 根据用户帐号获取用户资料
//    @Transactional(readOnly = true)
//    public MemberProfileBean getProfileInfo(int member_profile_id) {
//        MemberProfileBean profile = memberProfileInterFace.select(member_profile_id);
//        if (profile != null) {
//            // 只返回帐号、姓名、生日、手机号码、性别等信息
//            MemberProfileBean simplifiedProfile = new MemberProfileBean();
//            simplifiedProfile.setUser_account(profile.getUser_account());
//            simplifiedProfile.setUsername(profile.getUsername());
//            simplifiedProfile.setBirthday(profile.getBirthday());
//            simplifiedProfile.setGender(profile.getGender());
//            simplifiedProfile.setPhone_number(profile.getPhone_number());
//
//            return simplifiedProfile;
//        } else {
//            return null;
//        }
//    }

