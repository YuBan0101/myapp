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
    
    List<Product> selectThisTypeProduct(@Param(value="type")String type);
    
    List<Product> searchProduct(@Param(value="brand")String brand,@Param(value="model")String model);
    
    Product searchProductDes(@Param(value="brand")String brand,@Param(value="model")String model);
    
    List<Product> searchProductByModel(@Param(value="model")String model);
    
    List<Product> searchProductByBrand(@Param(value="brand")String brand);
    
    void updateReduceProductCount(@Param(value="brand")String brand,@Param(value="model")String model, @Param(value="count")Integer count);
    
    void updateAddProductCount(@Param(value="brand")String brand,@Param(value="model")String model, @Param(value="count")Integer count);
}