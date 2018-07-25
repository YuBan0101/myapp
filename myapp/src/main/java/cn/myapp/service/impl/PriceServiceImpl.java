package cn.myapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.myapp.dao.PriceDao;
import cn.myapp.dao.ProductDao;
import cn.myapp.dao.StoreDao;
import cn.myapp.model.Page;
import cn.myapp.model.Price;
import cn.myapp.model.Store;
import cn.myapp.service.PriceService;
@Service("PriceService")
public class PriceServiceImpl implements PriceService {

	@Resource
	private PriceDao priceDao;
	@Resource
	private StoreDao storeDao;
	@Resource
	private ProductDao productDao;
	@Override
	//获取所有price表所有记录 
	public List<Price> getAllPriceRecord(Page page) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Price> list = new ArrayList<Price>();
		page.setPageOffset();
		list = priceDao.selectAllPriceRecord(page);
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
	//获取所有price表所有记录  数量
	@Override
	public Page getAllPriceRecordCount(Page page) {
		page.setPageCount(priceDao.selectAllPriceRecordCount(page));
		return page;
	}
	@Override
	public List<Price> searchPriceRecord(Page page) {
		List<Price> list = new ArrayList<Price>();
		ArrayList<String> arr = new ArrayList<String>();
		Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+|[a-zA-Z0-9\\-.]+");
		Matcher m = p.matcher(page.getKey().trim());
        while ( m.find() ) {
            arr.add(m.group());
        }
        page.setPageOffset();
      //此处有逻辑错误 
        if(arr.size()==1 && arr.get(0).matches("[\\u4e00-\\u9fa5]+") == false) {
        	page.setModel(arr.get(0));
        	list = priceDao.searchPriceRecordByModel(page);
        }else if(arr.size()==1 && arr.get(0).matches("[\\u4e00-\\u9fa5]+") == true) {
        	page.setBrand(arr.get(0));
        	list = priceDao.searchPriceRecordByBrand(page);
        }
        else {
        	page.setBrand(arr.get(0));
        	page.setModel(arr.get(1));
        	list = priceDao.searchPriceRecord(page);
        }
        for(int i=0;i<list.size();i++) {
        	list.get(i).setType(productDao.searchProductDes(list.get(i).getBrand(), list.get(i).getModel()).getType());
        }
        
		return list;
	}
	@Override
	public Page searchPriceRecordCount(Page page) {
		ArrayList<String> arr = new ArrayList<String>();
		Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+|[a-zA-Z0-9\\-.]+");
		Matcher m = p.matcher(page.getKey().trim());
        while ( m.find() ) {
            arr.add(m.group());
        }
      //此处有逻辑错误 
        if(arr.size()==1 && arr.get(0).matches("[\\u4e00-\\u9fa5]+") == false) {
        	page.setModel(arr.get(0));
        	page.setPageCount(priceDao.selectSearchedPriceRecordCountByModel(page));
        }else if(arr.size()==1 && arr.get(0).matches("[\\u4e00-\\u9fa5]+") == true) {
        	page.setBrand(arr.get(0));
        	page.setPageCount(priceDao.selectSearchedPriceRecordCountByBrand(page));
        }
        else {
        	page.setBrand(arr.get(0));
        	page.setModel(arr.get(1));
        	page.setPageCount(priceDao.selectSearchedPriceRecordCount(page));
        }
        
		return page;
	}

	
	
	
}
