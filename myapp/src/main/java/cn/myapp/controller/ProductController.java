package cn.myapp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.myapp.model.Page;
import cn.myapp.model.Product;
import cn.myapp.service.ProductService;

@Controller
@RequestMapping("product")
public class ProductController {

	@Resource
	private ProductService productService;
	
	@RequestMapping(value ="/showAllProduct",method=RequestMethod.GET)
	@ResponseBody
	//显示所有商品
	public List<Product> showAllProduct(Page page) {
		
		return productService.getAllProduct(page);
	}
	
	@RequestMapping(value ="/showAllProductCount",method=RequestMethod.GET)
	@ResponseBody
	//显示所有商品个数
	public Page showAllProductCount(Page page) {
		
		return productService.getAllProductCount(page);
	}
	
	@RequestMapping(value ="/showAllProductType",method=RequestMethod.GET)
	@ResponseBody
	//获取所有商品类别
	public List<String> showAllProductType() {
		
		return productService.getAllProductType();
	}
	
	@RequestMapping(value ="/showThisTypeProduct",method=RequestMethod.GET)
	@ResponseBody
	//显示当前type所以商品
	public List<Product> showThisTypeProduct(HttpServletRequest req) {
		String type = req.getParameter("type");
		return productService.getThisTypeProduct(type);
	}
	
	@RequestMapping(value ="/searchProduct",method=RequestMethod.GET)
	@ResponseBody
	//显示当前type所以商品
	public List<Product> searchProduct(HttpServletRequest req) {
		String key = req.getParameter("keyword");
		return productService.searchProduct(key);
	}
	
	
	@RequestMapping(value ="/showAllShortSupplyProduct",method=RequestMethod.GET)
	@ResponseBody
	//获取所有商品类别
	public List<Product> showAllShortSupplyProduct() {
		
		return productService.getAllShortSupplyProduct();
	}
	
	@RequestMapping(value ="/showThisTypeShortSupplyProduct",method=RequestMethod.GET)
	@ResponseBody
	//显示当前type所以商品
	public List<Product> showThisTypeShortSupplyProduct(HttpServletRequest req) {
		String type = req.getParameter("type");
		return productService.getThisTypeShotSupplyProduct(type);
	}
}
