package cn.myapp.service;

import java.util.List;

import cn.myapp.model.Page;
import cn.myapp.model.Price;
import cn.myapp.model.Store;

public interface PriceService {
	//所有记录
	List<Price> getAllPriceRecord(Page page);
	//所有记录数量
	Page getAllPriceRecordCount(Page page);
	
	//查找入库记录
	public List<Price> searchPriceRecord(Page page);
	//查找入库记录 数量
	public Page searchPriceRecordCount(Page page);
}
