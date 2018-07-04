package cn.myapp.service;

import java.util.List;

import cn.myapp.model.Page;
import cn.myapp.model.Product;

public interface ProductService {
	
	//获取所有产品
	public List<Product> getAllProduct(Page page);
	
	//获取所有类别
	public List<String> getAllProductType();
	
	//获取所有type产品
	public List<Product> getThisTypeProduct(Page page);

	//查找产品
	public List<Product> searchProduct(Page page);
	
	public List<Product> getAllShortSupplyProduct();
	
	public List<Product> getThisTypeShotSupplyProduct(String type);
	
	//获取所有产品的个数
	public Page getAllProductCount(Page page);
	
	//获取type产品的个数
	public Page getThisTypeProductCount(Page page);
	
	//获取type产品的个数
	public Page getSearchedProductCount(Page page);
		

}
