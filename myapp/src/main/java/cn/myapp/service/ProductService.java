package cn.myapp.service;

import java.util.List;

import cn.myapp.model.Page;
import cn.myapp.model.Product;

public interface ProductService {
	//获取所有产品
	public List<Product> getAllProduct(Page page);
	
	public List<String> getAllProductType();
	
	public List<Product> getThisTypeProduct(String type);

	public List<Product> searchProduct(String key);
	
	public List<Product> getAllShortSupplyProduct();
	
	public List<Product> getThisTypeShotSupplyProduct(String type);
	
	

}
