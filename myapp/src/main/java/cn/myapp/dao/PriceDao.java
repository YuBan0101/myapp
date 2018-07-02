package cn.myapp.dao;

import org.apache.ibatis.annotations.Param;

import cn.myapp.model.Price;
import cn.myapp.model.Store;

public interface PriceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Price record);

    int insertSelective(Price record);

    Price selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Price record);

    int updateByPrimaryKey(Price record);
    
    //根据model brand 查找记录
    Price selectPriceByModelAndBrand(@Param(value="brand")String brand,@Param(value="model")String model);
    
    //更新价格
    void updatePrice(Store record);
}