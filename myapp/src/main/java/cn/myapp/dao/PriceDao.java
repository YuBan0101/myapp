package cn.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.myapp.model.Page;
import cn.myapp.model.Price;
import cn.myapp.model.Store;

public interface PriceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Price record);

    //根据Store插入
    int insertSelective(Store record);
    
    Price selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Price record);

    int updateByPrimaryKey(Price record);
    
    //根据model brand 查找记录
    Price selectPriceByModelAndBrand(@Param(value="brand")String brand,@Param(value="model")String model);
    
    //更新价格
    void updatePrice(Store record);
    
    //取得所有进价
    List<Price> selectAllPriceRecord(Page page);
    
  //取得所有进价 记录个数
    int selectAllPriceRecordCount(Page page);
    

}