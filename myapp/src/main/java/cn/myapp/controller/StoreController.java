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
@RequestMapping("store")
public class StoreController {
	
	private static Logger log=LoggerFactory.getLogger(StoreController.class);
	@Resource
	private StoreService storeService = null;
	@RequestMapping(value ="/showThisMonthStoreCount",method=RequestMethod.GET)
	@ResponseBody
	//获取本月进货数量
	public String showThisMonthStoreCount() {

		return JSON.toJSONString(String.valueOf(storeService.getThisMonthStoreCount()));
	}
	
}
