package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dto.Article;
import com.example.demo.dto.ResultData;

@Service
public class ArticleService {
	ArticleDao articledao = new ArticleDao();
	
	public Article CheckId(int id) {
		if(articledao.getArticle(id)==null) {
			return null;
		}
		else return articledao.getArticle(id);
	}

	public ResultData doWrite(String title, String body) {
		if(articledao.doWrite(title, body)) {
			return new ResultData("S-1","게시글 등록이 완료되었습니다.");
		}
		else {
			return new ResultData("F-3","게시글 등록에 실패했습니다.");
		}
	}

	public ResultData doModify(int id, String title, String body) {
		Article article = articledao.doModify(id,title,body);
		
		if(article==null) {
			return new ResultData("F-4","게시글 등록에 실패했습니다.");
		}
		
		return new ResultData("S-1","게시글 수정에 성공했습니다.","수정후",article);
	}

	public ResultData doDelete(int id) {
		if(!articledao.doDelete(id)) {
			return new ResultData("F-2","해당 게시글 삭제에 실패했습니다.");
		}
		return new ResultData("S-1","해당 게시글을 삭제했습니다.");
	}

	public ResultData showAll() {
		List<Article> articles = articledao.getArticles();
		if(articles==null) {
			return null;
		}
		return new ResultData("S-1","모든 게시글을 불러 옵니다.","모든 게시글",articles);
	}
}









