package cn.myapp.service;

import java.util.List;

import cn.myapp.model.Page;
import cn.myapp.model.Product;

public interface ProductService {
	
	//获取所有产品
	public List<Product> getAllProduct(Page page);
	
	//获取所有类别
	public List<String> getAllProductType(Page page);
	public List<String> getAllProductType();
	public Page getAllProductTypeCount(Page page);
	
	//获取所有type产品
	public List<Product> getThisTypeProduct(Page page);

	//查找产品
	public List<Product> searchProduct(Page page);
	
	// 短缺产品
	public List<Product> getAllShortSupplyProduct(Page page);
	// 短缺产品数量
	public Page getAllShortSupplyProductCount(Page page);
	
	//短缺产品 指定type
	public List<Product> getThisTypeShotSupplyProduct(Page page);
	//短缺产品数量    指定type
	public Page getThisTypeShotSupplyProductCount(Page page);
	
	//获取所有产品的个数
	public Page getAllProductCount(Page page);
	
	//获取type产品的个数
	public Page getThisTypeProductCount(Page page);
	
	//获取type产品的个数
	public Page getSearchedProductCount(Page page);
		

}
