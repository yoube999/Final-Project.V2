package tw.com.eeit168.products.attraction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit168.products.attraction.model.AttractionBean;

//JpaRepository 是 Spring Data JPA 提供的一個介面，它提供了一組通用的資料庫操作方法，例如插入、更新、刪除和查詢
public interface AttractionRepository extends JpaRepository<AttractionBean, Integer>, AttractionRepositoryDAO {

}
