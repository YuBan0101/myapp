package cn.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.myapp.model.Deliver;
import cn.myapp.model.Product;

public interface DeliverDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Deliver record);

    int insertSelective(Deliver record);

    Deliver selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Deliver record);

    int updateByPrimaryKey(Deliver record);
    
    int selectThisMonthDeliverCount();
    
    double selectThisMonthDeliverPrice();
    
    double selectThisMonthProfit();
    
    Deliver selectLastDeliverDate(@Param(value="brand")String brand,@Param(value="model")String model);
    
    List<Deliver> selectDeliverRecord();
    
    List<Deliver> selectAllDeliverRecord();
    
    List<Deliver> selectThisTypeDeliverRecord(Deliver deliver);
    
    List<Deliver> searchDeliverRecord(@Param(value="brand")String brand,@Param(value="model")String model);
    
    List<Deliver> searchDeliverRecordByModel(@Param(value="model")String model);
    
    List<Deliver> searchDeliverRecordByBrand(@Param(value="brand")String brand);
    
    
}