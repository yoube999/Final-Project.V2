package tw.com.eeit168.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit168.comment.model.RateCommentBean;

public interface RateCommentRepository extends JpaRepository<RateCommentBean, Integer> {

}
