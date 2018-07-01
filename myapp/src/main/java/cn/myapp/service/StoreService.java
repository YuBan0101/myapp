package cn.myapp.service;

import java.util.List;

import cn.myapp.model.Store;

public interface StoreService {

	public int getThisMonthStoreCount();
	//获取所有入库记录
	public List<Store> getAllStoreRecord();
	//获取指定type的入库记录
	public List<Store> getThisTypeStoreRecord(String type);
	//查找入库记录
	public List<Store> searchStoreRecord(String key);
}
