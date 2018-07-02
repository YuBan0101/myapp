package cn.myapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.myapp.dao.ProductDao;
import cn.myapp.dao.StoreDao;
import cn.myapp.model.Product;
import cn.myapp.model.Store;
import cn.myapp.service.StoreService;

@Service("StoreService")
public class StoreServiceImpl implements StoreService {

	@Resource
	private StoreDao storeDao;
	@Resource
	private ProductDao productDao;
	@Override
	//获取当月入库记录
	public int getThisMonthStoreCount() {
		//思想出现问题  ，应用一个新表来更新存储进价 ，进价不可能一成不变
		//int count = 0;
		//List<Store> list = new ArrayList<Store>();
		//list = storeDao.selectThisMonthStoreCount();
		//for(int i = 0; i < list.size(); i++) {
		//	count = count +list.get(i).getCount();
		//}
		
		return storeDao.selectThisMonthStoreCount();
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
	
	@Override
	//@Transactional
	//插入一条记录   product.count + store.count
	public Store getStoreRecordAfterAdd(Store record) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		record.setDate(new Date());
		record.setType(productDao.searchProductDes(record.getBrand(), record.getModel()).getType());
		//插入
		storeDao.insertSelective(record);
		//count+store.count
		
		productDao.updateAddProductCount(record.getBrand(), record.getModel(),record.getCount());
		//设置入库时间
		
		record = storeDao.selectLastStoreDate(record.getBrand(), record.getModel());
		record.setDateString(ft.format(record.getDate()));
		return record;
	}

}
