package cn.myapp.service;

import java.util.List;

import cn.myapp.model.Deliver;
import cn.myapp.model.Product;

public interface DeliverService {
	
	public int getThisMonthDeliverCount();

	public double getThisMonthDeliverPrice();
	
	public double getThisMonthProfit();
	
	public Deliver getDeliverRecordOne(Deliver record);
	
	public List<Deliver> getDeliverRecord();
	
	public List<Deliver> getAllDeliverRecord();
	
	public List<Deliver> getThisTypeDeliverRecord(String type);
	
	public List<Deliver> searchDeliverRecord(String key);
	
}
