package cn.myapp.service;

import java.util.List;

import cn.myapp.model.Product;

public interface ProductService {
	
	public List<Product> getAllProduct();
	
	public List<String> getAllProductType();
	
	public List<Product> getThisTypeProduct(String type);

	public List<Product> searchProduct(String key);
	
	public List<Product> getAllShortSupplyProduct();
	
	public List<Product> getThisTypeShotSupplyProduct(String type);
	
	

}
