package cn.myapp.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.myapp.model.Deliver;

public interface DeliverDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Deliver record);

    int insertSelective(Deliver record);

    Deliver selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Deliver record);

    int updateByPrimaryKey(Deliver record);
    
    int selectThisMonthDeliverCount();
    
    int selectThisMonthDeliverPrice();
    
    int selectThisMonthProfit();
    
    Deliver selectLastDeliverDate(@Param(value="brand")String brand,@Param(value="model")String model);
    
    
}