package me.wisdom.springbootdeveloper.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.wisdom.springbootdeveloper.domain.Article;
import me.wisdom.springbootdeveloper.dto.AddArticleRequest;
import me.wisdom.springbootdeveloper.dto.UpdateArticleRequest;
import me.wisdom.springbootdeveloper.repository.BlogRepository;

@RequiredArgsConstructor
@Service
public class BlogService {

	private final BlogRepository blogRepository;

	public Article save(AddArticleRequest request) {
		return blogRepository.save(request.toEntity());
	}

	public List<Article> findAll() {
		return blogRepository.findAll();
	}

	public Article findById(long id) {
		return blogRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("not found: " + id));
	}

	public void delete(long id) {
		blogRepository.deleteById(id);
	}

	@Transactional
	public Article update(long id, UpdateArticleRequest request) {
		Article article = blogRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("not found: " + id));

		article.update(request.getTitle(), request.getContent());

		return article;
	}
}
