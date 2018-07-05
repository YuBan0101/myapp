package cn.myapp.service;

import java.util.List;

import cn.myapp.model.Page;
import cn.myapp.model.Price;

public interface PriceService {
	//所有记录
	List<Price> getAllPriceRecord(Page page);
	//所有记录数量
	Page getAllPriceRecordCount(Page page);
}
