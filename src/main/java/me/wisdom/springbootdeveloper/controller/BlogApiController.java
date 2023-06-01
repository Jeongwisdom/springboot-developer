package me.wisdom.springbootdeveloper.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.wisdom.springbootdeveloper.domain.Article;
import me.wisdom.springbootdeveloper.dto.AddArticleRequest;
import me.wisdom.springbootdeveloper.dto.ArticleResponse;
import me.wisdom.springbootdeveloper.dto.UpdateArticleRequest;
import me.wisdom.springbootdeveloper.service.BlogService;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

	private final BlogService blogService;

	@GetMapping("/api/articles")
	public ResponseEntity<List<ArticleResponse>> findAlLArticles() {
		List<ArticleResponse> articles = blogService.findAll()
			.stream()
			.map(ArticleResponse::new)
			.toList();

		return ResponseEntity.ok()
			.body(articles);
	}

	@PostMapping("/api/articles")
	public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
		Article savedArticle = blogService.save(request);

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(savedArticle);
	}

	@GetMapping("/api/articles/{id}")
	public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
		Article article = blogService.findById(id);

		return ResponseEntity.ok()
			.body(new ArticleResponse(article));
	}

	@DeleteMapping("/api/articles/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
		blogService.delete(id);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/api/articles/{id}")
	public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) {
		Article updatedArticle = blogService.update(id, request);

		return ResponseEntity.ok()
			.body(updatedArticle);
	}
}
