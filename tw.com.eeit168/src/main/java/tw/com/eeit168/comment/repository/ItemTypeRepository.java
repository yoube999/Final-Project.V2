package tw.com.eeit168.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit168.comment.model.ItemTypeBean;

public interface ItemTypeRepository extends JpaRepository<ItemTypeBean, Integer> {

}
