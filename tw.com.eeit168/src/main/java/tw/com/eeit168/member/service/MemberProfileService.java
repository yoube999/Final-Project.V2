package tw.com.eeit168.member.service;

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
}
