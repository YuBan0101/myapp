package cn.myapp.service;

import java.util.List;

import cn.myapp.model.Deliver;

public interface DeliverService {
	
	public int getThisMonthDeliverCount();

	public double getThisMonthDeliverPrice();
	
	public double getThisMonthProfit();
	
	public Deliver getDeliverRecordOne(Deliver record);
	
	public List<Deliver> getDeliverRecord();
	
}
