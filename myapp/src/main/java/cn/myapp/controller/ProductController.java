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
	public List<String> showAllProductType(Page page) {
		
		return productService.getAllProductType(page);
	}
	@RequestMapping(value ="/showAllProductType1",method=RequestMethod.GET)
	@ResponseBody
	//获取所有商品类别
	public List<String> showAllProductType1() {
		
		return productService.getAllProductType1();
	}
	
	@RequestMapping(value ="/showAllProductTypeCount",method=RequestMethod.GET)
	@ResponseBody
	//获取所有商品类别 数量
	public Page showAllProductTypeCount(Page page) {
		
		return productService.getAllProductTypeCount(page);
	}
	
	@RequestMapping(value ="/showThisTypeProduct",method=RequestMethod.GET)
	@ResponseBody
	//显示当前type所以商品
	public List<Product> showThisTypeProduct(Page page) {
		
		return productService.getThisTypeProduct(page);
	}
	
	@RequestMapping(value ="/showThisTypeProductCount",method=RequestMethod.GET)
	@ResponseBody
	//显示当前type商品个数
	public Page showThisTypeProductCount(Page page) {
		
		return productService.getThisTypeProductCount(page);
	}
	
	@RequestMapping(value ="/searchProduct",method=RequestMethod.GET)
	@ResponseBody
	//显示当前keyword所有商品
	public List<Product> searchProduct(Page page) {
		return productService.searchProduct(page);
	}
	
	@RequestMapping(value ="/searchProductCount",method=RequestMethod.GET)
	@ResponseBody
	//显示当前keyword所有商品   个数
	public Page searchProductCount(Page page) {
		return productService.getSearchedProductCount(page);
	}
	
	
	@RequestMapping(value ="/showAllShortSupplyProduct",method=RequestMethod.GET)
	@ResponseBody
	//获取所有商品类别
	public List<Product> showAllShortSupplyProduct(Page page) {
		
		return productService.getAllShortSupplyProduct(page);
	}
	
	@RequestMapping(value ="/showThisTypeShortSupplyProduct",method=RequestMethod.GET)
	@ResponseBody
	//显示当前type所以商品
	public List<Product> showThisTypeShortSupplyProduct(HttpServletRequest req,Page page) {
		return productService.getThisTypeShotSupplyProduct(page);
	}
	
	@RequestMapping(value ="/showAllShortSupplyProductCount",method=RequestMethod.GET)
	@ResponseBody
	//获取所有短缺产品   数量
	public Page showAllShortSupplyProductCount(Page page) {
		
		return productService.getAllShortSupplyProductCount(page);
	}
	
	@RequestMapping(value ="/showThisTypeShortSupplyProductCount",method=RequestMethod.GET)
	@ResponseBody
	//获取指定Type 短缺产品 数量
	public Page showThisTypeShortSupplyProductCount(HttpServletRequest req,Page page) {
		return productService.getThisTypeShotSupplyProductCount(page);
	}
}
