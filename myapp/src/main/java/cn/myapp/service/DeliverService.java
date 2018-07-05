package cn.myapp.service;

import java.util.List;

import cn.myapp.model.Deliver;
import cn.myapp.model.Page;
import cn.myapp.model.Product;

public interface DeliverService {
	
	public int getThisMonthDeliverCount();

	public double getThisMonthDeliverPrice();
	
	public double getThisMonthProfit();
	
	public Deliver getDeliverRecordAfterAdd(Deliver record);
	
	public List<Deliver> getDeliverRecord();
	
	//获取所有出库记录
	public List<Deliver> getAllDeliverRecord(Page page);
	
	//获取指定type出库记录
	public List<Deliver> getThisTypeDeliverRecord(Page page);
	
	
	//查找关键字 出库记录
	public List<Deliver> searchDeliverRecord(Page page);
	public Page searchDeliverRecordCount(Page page);

	//获取所有入库记录 数量
	public Page getAllDeliverRecordCount(Page page);
	
	// 获取指定type 入库记录 数量
	public Page getThisTypeDeliverRecordCount(Page page);
	
}
