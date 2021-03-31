package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.Article;
import com.example.demo.util.Util;

@Component
public class ArticleDao {
	private List<Article> articles;
	private int articleLastId;

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public ArticleDao() {
		articles = new ArrayList<>();
		articleLastId = 0;
		makeTestData();
	}

	private void makeTestData() {
		for (int i = 1; i <= 11; i++) {
			writeArticle("제목" + i, "내용" + i);
		}
	}

	public int writeArticle(String title, String body) {
		int id = articleLastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = Util.getNowDateStr();

		Article article = new Article(id, title, body, regDate, updateDate);
		articles.add(article);

		articleLastId = id;

		return id;
	}

	public Article getArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	public boolean doWrite(String title, String body) {
		if (writeArticle(title, body) == articleLastId) {
			return true;
		}
		return false;
	}

	public Article doModify(int id, String title, String body) {
		Article article = getArticle(id);
		if(article==null)return null;
		article.setTitle(title);
		article.setBody(body);
		
		articles.set(id, article);
		return article;
	}

	public boolean doDelete(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				articles.remove(article);
				return true;
			}
		}
		return false;
	}

}
