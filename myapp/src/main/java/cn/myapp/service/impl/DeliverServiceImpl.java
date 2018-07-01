package cn.myapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.myapp.dao.DeliverDao;
import cn.myapp.model.Deliver;
import cn.myapp.service.DeliverService;

@Service("DeliverService")
public class DeliverServiceImpl implements DeliverService{

	@Resource
	private DeliverDao deliverDao;
	
	@Override
	public int getThisMonthDeliverCount() {
		
		return deliverDao.selectThisMonthDeliverCount();
	}
	@Override
	public double getThisMonthDeliverPrice() {
		
		return deliverDao.selectThisMonthDeliverPrice();
	}
	@Override
	public double getThisMonthProfit() {
		
		return deliverDao.selectThisMonthProfit();
	}
	@Override
	@Transactional
	public Deliver getDeliverRecordOne(Deliver record) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		record.setDate(new Date());
		deliverDao.insertSelective(record);
		record = deliverDao.selectLastDeliverDate(record.getBrand(), record.getModel());
		record.setDateString(ft.format(record.getDate()));
		return record;
	}
	@Override
	public List<Deliver> getDeliverRecord() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		List<Deliver> list = new ArrayList<Deliver>();
		list = deliverDao.selectDeliverRecord();
		for(int i=0;i<list.size();i++) {
			list.get(i).setDateString(ft.format(list.get(i).getDate()));
		}
		return list;
	}
	
}


