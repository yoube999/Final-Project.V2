package tw.com.eeit168.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit168.helpdesk.dao.HelpDeskMemberDAO;
import tw.com.eeit168.member.model.MemberProfileBean;

public interface HelpDeskMemberRepository extends HelpDeskMemberDAO, JpaRepository<MemberProfileBean, Integer> {

}
