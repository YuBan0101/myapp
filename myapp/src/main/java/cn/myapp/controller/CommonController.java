package cn.myapp.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.myapp.model.Jsdata;
import cn.myapp.service.DeliverService;
import cn.myapp.service.PriceService;

@Controller
@RequestMapping("/common")
public class CommonController {
	
	@Resource
	PriceService priceService;
	
	@Resource
	DeliverService deliverService;
	
	//获取当月销售金额  按月份销售返回  返回格式
	/*var areaChartData = {
		      labels  : ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
		      datasets: [
		        {
		          label               : 'Electronics',
		          fillColor           : 'rgba(210, 214, 222, 1)',
		          strokeColor         : 'rgba(210, 214, 222, 1)',
		          pointColor          : 'rgba(210, 214, 222, 1)',
		          pointStrokeColor    : '#c1c7d1',
		          pointHighlightFill  : '#fff',
		          pointHighlightStroke: 'rgba(220,220,220,1)',
		          data                : [65, 59, 80, 81, 56, 55, 40]
		        },
		        {
		          label               : 'Digital Goods',
		          fillColor           : 'rgba(60,141,188,0.9)',
		          strokeColor         : 'rgba(60,141,188,0.8)',
		          pointColor          : '#3b8bba',
		          pointStrokeColor    : 'rgba(60,141,188,1)',
		          pointHighlightFill  : '#fff',
		          pointHighlightStroke: 'rgba(60,141,188,1)',
		          data                : [28, 48, 40, 19, 86, 27, 90]
		        }
		      ]
		    }*/
	@RequestMapping("/showMonthPrice")
	@ResponseBody
	public Map<String,Object> showMonthPrice(String year) {
		return deliverService.getMonthSalesMoney(year);
		
	}
	
	
	@RequestMapping("/showYear")
	@ResponseBody
	public List<Jsdata> showYear() {
		return deliverService.getYear();
		
	}
}
