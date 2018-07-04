package cn.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.myapp.model.Page;
import cn.myapp.model.Product;
import cn.myapp.model.Store;

public interface ProductDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int insertByStore(Store record);
    
    int updateByPrimaryKey(Product record);
    
    //获取所有产品
    List<Product> selectAllProduct(Page page);
    
    //获取全部产品个数
    int selectAllProductCount();
  
    List<Product> selectAllProductType();
    
    List<Product> selectAllShortSupplyProduct();
    
    List<Product> selectThisTypeShortSupplyProduct(@Param(value="type")String type);
    
    ////获取所有type产品
    List<Product> selectThisTypeProduct(Page page);
    
   //获取type产品个数
    int selectThisTypeProductCount(Page page);

    //获取产品 by model brand
    List<Product> searchProduct(Page page);
    Product searchProductDes(@Param(value="brand")String brand,@Param(value="model")String model);
    List<Product> searchProductByModel(Page page);
    List<Product> searchProductByBrand(Page page);
    
    
    //获取产品数量 by model  brand
    int searchProductCount(Page page);
    int searchProductCountByModel(Page page);
    int searchProductCountByBrand(Page page);

    
    void updateReduceProductCount(@Param(value="brand")String brand,@Param(value="model")String model, @Param(value="count")Integer count);
    
    void updateAddProductCount(@Param(value="brand")String brand,@Param(value="model")String model, @Param(value="count")Integer count);
}