package cn.myapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.myapp.dao.PriceDao;
import cn.myapp.dao.StoreDao;
import cn.myapp.model.Price;
import cn.myapp.service.PriceService;
@Service("PriceService")
public class PriceServiceImpl implements PriceService {

	@Resource
	private PriceDao priceDao;
	@Resource
	private StoreDao storeDao;
	@Override
	//获取所有price表所有记录 
	public List<Price> getAllPriceRecord() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		List<Price> list = new ArrayList<Price>();
		list = priceDao.selectAllPriceRecord();
		for(int i=0;i<list.size();i++) {
			if(storeDao.selectTwoLastStoreRecord(list.get(i).getBrand(), list.get(i).getModel()).size() == 1) {
				list.get(i).setDateNow("无更新");
				list.get(i).setDatePass(ft.format(storeDao.selectTwoLastStoreRecord(list.get(i).getBrand(), list.get(i).getModel()).get(0).getDate()));
				list.get(i).setOldPrice(storeDao.selectTwoLastStoreRecord(list.get(i).getBrand(), list.get(i).getModel()).get(0).getPrice());
			}
			else {
			list.get(i).setDateNow(ft.format(storeDao.selectTwoLastStoreRecord(list.get(i).getBrand(), list.get(i).getModel()).get(0).getDate()));
			list.get(i).setDatePass(ft.format(storeDao.selectTwoLastStoreRecord(list.get(i).getBrand(), list.get(i).getModel()).get(1).getDate()));
			list.get(i).setOldPrice(storeDao.selectTwoLastStoreRecord(list.get(i).getBrand(), list.get(i).getModel()).get(1).getPrice());
			}
		}
		return list;
	}

}
