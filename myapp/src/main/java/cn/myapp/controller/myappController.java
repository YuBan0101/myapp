package cn.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hh")
public class myappController {

	@RequestMapping("/index")
	public String index() {
		
		return "demo";
	}
}