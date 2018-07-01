package cn.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.myapp.model.Product;

public interface ProductDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    
    List<Product> selectAllProduct();
    
    List<Product> selectAllProductType();
    
    List<Product> selectAllShortSupplyProduct();
    
    List<Product> selectThisTypeShortSupplyProduct(@Param(value="type")String type);
    
    List<Product> selectThisTypeProduct(@Param(value="type")String type);
    
    List<Product> searchProduct(@Param(value="brand")String brand,@Param(value="model")String model);
    
    Product searchProductDes(@Param(value="brand")String brand,@Param(value="model")String model);
    
    List<Product> searchProductByModel(@Param(value="model")String model);
    
    List<Product> searchProductByBrand(@Param(value="brand")String brand);
    
    void updateProductCount(@Param(value="brand")String brand,@Param(value="model")String model);
    
}