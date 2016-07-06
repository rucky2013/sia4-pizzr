package cc.pizzr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cc.pizzr.entity.Article;

/**
 * 
 * @author lijinting01
 * 
 */
public interface ArticleRepository extends JpaRepository<Article, String> {
	/**
	 * 
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT * FROM Article a WHERE a.author = ?1")
	List<Article> findByAuthor(String author);
}
