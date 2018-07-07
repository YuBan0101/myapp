package cn.myapp.controller;


import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.myapp.model.Account;
import cn.myapp.model.Page;
import cn.myapp.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	private static Logger log=LoggerFactory.getLogger(AccountController.class);
	
	@Resource
	AccountService accountService;
	
	@RequestMapping(value="/showAccountInfo",method = RequestMethod.GET)
	@ResponseBody
	//获取账单信息
	public List<Account> showAccountInfo(Page record) {
		
		
		return accountService.getAccountInfo(record);
	}
	
	
	
	

}
