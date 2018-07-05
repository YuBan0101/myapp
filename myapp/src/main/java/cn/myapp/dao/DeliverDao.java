package cn.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.myapp.model.Deliver;
import cn.myapp.model.Page;
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
    
    //获取所有出库记录    数量
    List<Deliver> selectAllDeliverRecord(Page page);
    int selectAllDeliverRecordCount(Page page);
    
    //获取指定 type 出库记录    数量
    List<Deliver> selectThisTypeDeliverRecord(Page page);
    int selectThisTypeDeliverRecordCount(Page page);
    
    
    // 查找 关键字 出库 信息
    List<Deliver> searchDeliverRecord(Page page);
    List<Deliver> searchDeliverRecordByModel(Page page);
    List<Deliver> searchDeliverRecordByBrand(Page page);
    
    // 查找 关键字 出库 信息   数量
    int searchDeliverRecordCount(Page page);
    int searchDeliverRecordByModelCount(Page page);
    int searchDeliverRecordByBrandCount(Page page);
    
    
}