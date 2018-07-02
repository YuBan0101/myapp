package cn.myapp.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.myapp.model.Price;
import cn.myapp.service.PriceService;

@Controller
@RequestMapping("/price")
public class PriceController {

	private static Logger log=LoggerFactory.getLogger(PriceController.class);
	@Resource
	private PriceService priceService;
	
	@RequestMapping(value="/showAllPriceRecord",method = RequestMethod.GET)
	@ResponseBody
	//显示所有Price记录
	public List<Price> showAllPriceRecord() {
		
		return priceService.getAllPriceRecord();
	}
	
	
	
}
