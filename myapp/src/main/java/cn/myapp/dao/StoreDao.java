package cn.myapp.dao;

import cn.myapp.model.Store;

public interface StoreDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);
    
    Store selectThisMonthStoreCount();
}