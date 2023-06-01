package me.wisdom.springbootdeveloper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.wisdom.springbootdeveloper.domain.Article;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
