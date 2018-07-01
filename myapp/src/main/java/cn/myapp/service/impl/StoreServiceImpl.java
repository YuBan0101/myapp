package cn.myapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.myapp.dao.StoreDao;
import cn.myapp.model.Product;
import cn.myapp.model.Store;
import cn.myapp.service.StoreService;

@Service("StoreService")
public class StoreServiceImpl implements StoreService {

	@Resource
	private StoreDao storeDao;
	@Override
	public int getThisMonthStoreCount() {
		int count = 0;
		List<Store> list = new ArrayList<Store>();
		list = storeDao.selectThisMonthStoreCount();
		for(int i = 0; i < list.size(); i++) {
			count = count +list.get(i).getCount();
		}
 
		return count;
	}
	@Override
	public List<Store> getAllStoreRecord() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		List<Store> list = new ArrayList<Store>();
		list = storeDao.selectAllStoreRecord();
		for(int i=0;i<list.size();i++) {
			list.get(i).setDateString(ft.format(list.get(i).getDate()));
		}
		return list;
	}
	@Override
	public List<Store> getThisTypeStoreRecord(String type) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		List<Store> list = new ArrayList<Store>();
		list = storeDao.selectThisTypeStoreRecord(type);
		for(int i=0;i<list.size();i++) {
			list.get(i).setDateString(ft.format(list.get(i).getDate()));
		}
		return list;
	}
	@Override
	public List<Store> searchStoreRecord(String key) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		List<Store> list = new ArrayList<Store>();
		ArrayList<String> arr = new ArrayList<String>();
		Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+|\\d+");
		Matcher m = p.matcher(key.trim());
        while ( m.find() ) {
            arr.add(m.group());
        }
      //此处有逻辑错误 
        if(arr.size()==1 && arr.get(0).matches("[\\u4e00-\\u9fa5]+") == false) {
        	list = storeDao.searchStoreRecordByModel(arr.get(0));
        }else if(arr.size()==1 && arr.get(0).matches("[\\u4e00-\\u9fa5]+") == true) {
        	list = storeDao.searchStoreRecordByBrand(arr.get(0));
        }
        else {
        	list = storeDao.searchStoreRecord(arr.get(0), arr.get(1));
        }
        for(int i=0;i<list.size();i++) {
        	
			list.get(i).setDateString((ft.format(storeDao.selectLastStoreDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
		}
		return list;
	}

}
