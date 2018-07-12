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
	
	@RequestMapping(value="/showAccountInfoCount",method = RequestMethod.GET)
	@ResponseBody
	//获取账单数量
	public Page showAccountInfoCount(Page record) {
		
		
		return accountService.getSelectAccountInfoCount(record);
	}
	
	@RequestMapping(value="/showUpdateInfo",method = RequestMethod.GET)
	@ResponseBody
	//账单状态
	public Account showUpdateInfo(Account record) {
		
		
		return accountService.getSelectAccountInfoById(record);
	}
	
	@RequestMapping(value="/showAccountInfoChange",method = RequestMethod.POST)
	@ResponseBody
	//账单状态
	public int showAccountInfoChange(Account record) {
		
		 return accountService.changeAccountInfo(record);
	}
	
	@RequestMapping(value="/addNewAccount",method = RequestMethod.POST)
	@ResponseBody
	//账单状态
	public int addNewAccount(Account record) {
		
		return accountService.addAccount(record);
	}
	
	
	@RequestMapping(value="/searchAccount")
	@ResponseBody
	//获取账单信息
	public List<Account> searchAccount(Page record) {
		
		
		return accountService.getSearchAccountInfo(record);
	}
	
	@RequestMapping(value="/searchAccountCount")
	@ResponseBody
	//获取账单数量
	public Page searchAccountCount(Page record) {
		
		
		return accountService.getSearchAccountInfoCount(record);
	}

}
