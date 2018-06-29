package cn.myapp.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.myapp.service.StoreService;

@Controller
public class StoreController {
	
	private static Logger log=LoggerFactory.getLogger(StoreController.class);
	@Resource
	private StoreService storeService = null;
	@RequestMapping(value ="/showThisMonthStoreCount",method=RequestMethod.GET)
	@ResponseBody
	public String showThisMonthStoreCount() {
		
		String s = String.valueOf(storeService.getThisMonthStoreCount());
		System.err.println(s);
		return JSON.toJSONString(s);
	}
	
}
