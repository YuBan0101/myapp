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

    //搜索
	List<Price> searchPriceRecordByModel(Page page);
	List<Price> searchPriceRecordByBrand(Page page);
	List<Price> searchPriceRecord(Page page);

	//搜索 count 数
	Integer selectSearchedPriceRecordCountByModel(Page page);
	Integer selectSearchedPriceRecordCountByBrand(Page page);
	Integer selectSearchedPriceRecordCount(Page page);
    

}