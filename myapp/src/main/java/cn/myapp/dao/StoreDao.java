package cn.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.myapp.model.Product;
import cn.myapp.model.Store;

public interface StoreDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);
    
    int selectThisMonthStoreCount();
    
    List<Store> selectAllStoreRecord();
    
    List<Store> selectThisTypeStoreRecord(@Param(value="type")String type);
    
    List<Store> searchStoreRecord(@Param(value="brand")String brand,@Param(value="model")String model);
    
    Store searchStoreRecordDes(@Param(value="brand")String brand,@Param(value="model")String model);
    
    List<Store> searchStoreRecordByModel(@Param(value="model")String model);
    
    List<Store> searchStoreRecordByBrand(@Param(value="brand")String brand);
    
    Store selectLastStoreDate(@Param(value="brand")String brand,@Param(value="model")String model);
}