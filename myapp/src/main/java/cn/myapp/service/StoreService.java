package cn.myapp.service;

import java.util.List;

import cn.myapp.model.Page;
import cn.myapp.model.Store;

public interface StoreService {

	public int getThisMonthStoreCount();
	//获取所有入库记录
	public List<Store> getAllStoreRecord(Page page);
	//获取所有入库记录 数量
	public Page getAllStoreRecordCount(Page page);
	
	
	//获取指定type的入库记录
	public List<Store> getThisTypeStoreRecord(Page page);
	//获取指定type的入库记录 数量
	public Page getThisTypeStoreRecordCount(Page page);
	
	//查找入库记录
	public List<Store> searchStoreRecord(Page page);
	//查找入库记录 数量
	public Page searchStoreRecordCount(Page page);
	
	//插入记录 并更新product.count
	public Store getStoreRecordAfterAdd(Store record);
	
}
