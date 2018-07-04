package cn.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.myapp.model.Page;
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
    //获取全部入库记录
    List<Store> selectAllStoreRecord(Page page);
    //获取全部入库记录数
    int selectAllStoreRecordCount(Page page);
    
    //获取指定入库记录
    List<Store> selectThisTypeStoreRecord(Page page);
    //获取指定入库记录数
    int selectThisTypeStoreRecordCount(Page page);
    
    //获取查找入库记录数
    int selectSearchedStoreRecordCount(Page page);
    int selectSearchedStoreRecordCountByModel(Page page);
    int selectSearchedStoreRecordCountByBrand(Page page);
    
    //查找 by brand model
    List<Store> searchStoreRecord(Page page);
    
    Store searchStoreRecordDes(Page page);
    
    List<Store> searchStoreRecordByModel(Page page);
    
    List<Store> searchStoreRecordByBrand(Page page);
    
    Store selectLastStoreDate(@Param(value="brand")String brand,@Param(value="model")String model);
    //查找最近插入的2条记录
    List<Store> selectTwoLastStoreRecord(@Param(value="brand")String brand,@Param(value="model")String model);
    
}