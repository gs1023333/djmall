package com.dj.mall.admin.web.auth.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/index/")
public class IndexPageController {


	@RequestMapping("index")
	public String index() {
		return "/index/index";
	}
	
	@RequestMapping("top")
	public String top() {
		return "/index/top";
	}
	
	@RequestMapping("left")
	public String left() {
		return "/index/left";
	}
	
	@RequestMapping("right")
	public String right() {
		return "/index/right";
	}
}
