package cn.myapp.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.myapp.dao.StoreDao;
import cn.myapp.model.Store;
import cn.myapp.service.StoreService;

@Service("StoreService")
public class StoreServiceImpl implements StoreService {

	@Resource
	private StoreDao storeDao;
	@Override
	public int getThisMonthStoreCount() {
		int count = 0;
		ArrayList<Store> list = new ArrayList<Store>();
		list.add(storeDao.selectThisMonthStoreCount());
		System.err.println(list.size());
		for(int i = 0; i < list.size(); i++) {
			count = count +list.get(i).getCount();
		}
 
		return count;
	}

}
