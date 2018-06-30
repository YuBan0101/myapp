package cn.myapp.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.myapp.model.Store;

public interface StoreDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);
    
    List<Store> selectThisMonthStoreCount();
    
    Store selectLastStoreDate(@Param(value="brand")String brand,@Param(value="model")String model);
}