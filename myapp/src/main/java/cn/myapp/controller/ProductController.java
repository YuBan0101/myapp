package cn.myapp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

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
	public List<Product> showAllProduct() {
		
		return productService.getAllProduct();
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
		System.err.println(type);
		return productService.getThisTypeProduct(type);
	}
}
