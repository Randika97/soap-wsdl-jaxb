package com.training.soap.repository;

import java.util.List;

import com.training.soap.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long>  {
	Article findByArticleId(long articleId);
    List<Article> findByTitleAndCategory(String title, String category);
}
