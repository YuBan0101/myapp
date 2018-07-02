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

import cn.myapp.model.Deliver;
import cn.myapp.service.DeliverService;
@Controller
@RequestMapping("/deliver")
public class DeliverController {
	
	private static Logger log=LoggerFactory.getLogger(DeliverController.class);
	@Resource
	private DeliverService deliverService;
	
	@RequestMapping(value="/showThisMonthDeliverCount",method = RequestMethod.GET)
	@ResponseBody
	//获取当月销售数量
	public String showThisMonthDeliverCount() {
		
		return JSON.toJSONString(String.valueOf(deliverService.getThisMonthDeliverCount()));
	}
	@RequestMapping(value="/showThisMonthDeliverPrice",method = RequestMethod.GET)
	@ResponseBody
	//获取当月累计金额
	public String showThisMonthDeliverPrice() {
		
		return JSON.toJSONString(String.valueOf(deliverService.getThisMonthDeliverPrice()));
	}
	
	@RequestMapping(value="/showThisMonthProfit",method = RequestMethod.GET)
	@ResponseBody
	//获取当月累计利润
	public String showThisMonthProfit() {
		
		return JSON.toJSONString(String.valueOf(deliverService.getThisMonthProfit()));
	}
	
	@RequestMapping(value="/showDeliverRecordAfterAdd")
	@ResponseBody
	//获取新插入一条信息
	public Deliver showDeliverRecordAfterAdd(Deliver record, HttpServletRequest req) {

		return deliverService.getDeliverRecordAfterAdd(record);
	}
	
	@RequestMapping(value="/showDeliverRecord")
	@ResponseBody
	//获取插入信息
	public List<Deliver> showDeliverRecord() {

		return deliverService.getDeliverRecord();
	}
	
	@RequestMapping(value="/showAllDeliverRecord")
	@ResponseBody
	//所有出库记录
	public List<Deliver> showAllDeliverRecord() {

		return deliverService.getAllDeliverRecord();
	}
	
	@RequestMapping(value="/showThisTypeDeliverRecord")
	@ResponseBody
	//type 信息
	public List<Deliver> showThisTypeDeliverRecord(HttpServletRequest req) {

		return deliverService.getThisTypeDeliverRecord(req.getParameter("type"));
	}
	
	@RequestMapping(value="/searchDeliverRecord")
	@ResponseBody
	//查找
	public List<Deliver> searchDeliverRecord(HttpServletRequest req) {

		return deliverService.searchDeliverRecord(req.getParameter("keyword"));
	}
}
