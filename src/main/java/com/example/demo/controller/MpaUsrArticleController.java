package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Article;
import com.example.demo.dto.ResultData;
import com.example.demo.service.ArticleService;

@Controller
public class MpaUsrArticleController {
	ArticleService articleservice = new ArticleService();
	
	@RequestMapping("/mpaUsr/article/showarticle")
	@ResponseBody
	public ResultData ShowArticleById(int id) {
		Article article = articleservice.CheckId(id);
		
		if(article==null) {
			return new ResultData("F-1","해당 게시글이 없습니다.");
		}
		
		return new ResultData("S-1","성공",id+"게시글 내용",article);
	}
	
	@RequestMapping("/mpaUsr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {
		if(title.equals("")) {
			return new ResultData("F-1","제목을 입력해주세요.");
		}
		if(body.equals("")) {
			return new ResultData("F-2","내용을 입력해주세요.");
		}
		
		return articleservice.doWrite(title, body);
	}
	
	@RequestMapping("/mpaUsr/article/doModify")
	@ResponseBody
	public ResultData doWrite(int id, String title, String body) {
		if(articleservice.CheckId(id)==null) {
			return new ResultData("F-1","해당 게시글이 없습니다.");
		}
		if(title.equals("")) {
			return new ResultData("F-2","제목을 입력해주세요.");
		}
		if(body.equals("")) {
			return new ResultData("F-3","내용을 입력해주세요.");
		}
		
		return articleservice.doModify(id, title, body);		
	}
	
	@RequestMapping("/mpaUsr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {
		if(articleservice.CheckId(id)==null) {
			return new ResultData("F-1","해당 게시글이 없습니다.");
		}
		
		return articleservice.doDelete(id);
	}
	
	@RequestMapping("/mpaUsr/article/showAll")
	@ResponseBody
	public ResultData showAll() {
		if(articleservice.showAll()==null) {
			return new ResultData("F-1","게시글이 없습니다.");
		}
		return articleservice.showAll();
	}
	
}






