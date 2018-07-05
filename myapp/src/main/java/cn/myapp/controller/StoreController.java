package cn.myapp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.myapp.model.Page;
import cn.myapp.model.Store;
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
	
	
	
	@RequestMapping(value ="/showAllStoreRecord",method=RequestMethod.GET)
	@ResponseBody
	//获取所有入库记录
	public List<Store> showAllStoreRecord(Page page) {

		return storeService.getAllStoreRecord(page);
	}
	
	@RequestMapping(value ="/showAllStoreRecordCount",method=RequestMethod.GET)
	@ResponseBody
	//获取所有入库记录 数量
	public Page showAllStoreRecordCount(Page page) {

		return storeService.getAllStoreRecordCount(page);
	}
	
	@RequestMapping(value ="/showThisTypeStoreRecord",method=RequestMethod.GET)
	@ResponseBody
	//获取指定type入库记录
	public List<Store> showThisTypeStoreRecord(Page page) {

		return storeService.getThisTypeStoreRecord(page);
	}
	
	@RequestMapping(value ="/showThisTypeStoreRecordCount",method=RequestMethod.GET)
	@ResponseBody
	//获取指定type入库记录   数量
	public Page showThisTypeStoreRecordCount(Page page) {

		return storeService.getThisTypeStoreRecordCount(page);
	}
	
	
	@RequestMapping(value ="/searchStoreRecord",method=RequestMethod.GET)
	@ResponseBody
	//搜索指定品牌或型号 入库记录
	public List<Store> searchStoreRecord(Page page) {

		return storeService.searchStoreRecord(page);
	}
	
	@RequestMapping(value ="/searchStoreRecordCount",method=RequestMethod.GET)
	@ResponseBody
	//搜索指定品牌或型号 入库记录  数量
	public Page searchStoreRecordCount(Page page) {

		return storeService.searchStoreRecordCount(page);
	}
	
	
	
	@RequestMapping(value ="/showStoreRecordAfterAdd")
	@ResponseBody
	//新录入入库记录
	public Store showStoreRecordAfterAdd(HttpServletRequest req,Store record) {

		return storeService.getStoreRecordAfterAdd(record);
	}
	
}
