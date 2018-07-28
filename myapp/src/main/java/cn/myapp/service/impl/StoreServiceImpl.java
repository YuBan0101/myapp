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

import cn.myapp.dao.PriceDao;
import cn.myapp.dao.ProductDao;
import cn.myapp.dao.StoreDao;
import cn.myapp.model.Page;
import cn.myapp.model.Store;
import cn.myapp.service.StoreService;

@Service("StoreService")
public class StoreServiceImpl implements StoreService {

	@Resource
	private StoreDao storeDao;
	@Resource
	private ProductDao productDao;
	@Resource
	private PriceDao priceDao;
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
	public List<Store> getAllStoreRecord(Page page) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		List<Store> list = new ArrayList<Store>();
		page.setPageOffset();
		list = storeDao.selectAllStoreRecord(page);
		for(int i=0;i<list.size();i++) {
			list.get(i).setDateString(ft.format(list.get(i).getDate()));
		}
		return list;
	}
	@Override
	public List<Store> getThisTypeStoreRecord(Page page) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		List<Store> list = new ArrayList<Store>();
		page.setPageOffset();
		list = storeDao.selectThisTypeStoreRecord(page);
		for(int i=0;i<list.size();i++) {
			list.get(i).setDateString(ft.format(list.get(i).getDate()));
		}
		return list;
	}
	@Override
	public List<Store> searchStoreRecord(Page page) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		List<Store> list = new ArrayList<Store>();
		ArrayList<String> arr = new ArrayList<String>();
		Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+|[a-zA-Z0-9\\-.*]+");
		Matcher m = p.matcher(page.getKey().trim());
        while ( m.find() ) {
            arr.add(m.group());
        }
        page.setPageOffset();
      //此处有逻辑错误 
        if(arr.size()==1 && arr.get(0).matches("[\\u4e00-\\u9fa5]+") == false) {
        	page.setModel(arr.get(0));
        	list = storeDao.searchStoreRecordByModel(page);
        }else if(arr.size()==1 && arr.get(0).matches("[\\u4e00-\\u9fa5]+") == true) {
        	page.setBrand(arr.get(0));
        	list = storeDao.searchStoreRecordByBrand(page);
        }
        else {
        	page.setBrand(arr.get(0));
        	page.setModel(arr.get(1));
        	list = storeDao.searchStoreRecord(page);
        }
        for(int i=0;i<list.size();i++) {
        	
			list.get(i).setDateString((ft.format(storeDao.selectLastStoreDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
		}
		return list;
	}
	
	@Override
	@Transactional
	//插入一条记录   product.count + store.count
	public Store getStoreRecordAfterAdd(Store record) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		record.setDate(new Date());
		//record.setType(productDao.searchProductDes(record.getBrand(), record.getModel()).getType());
		if(storeDao.selectByPrimaryKey(record.getId()) != null) {
			Store temp = storeDao.selectByPrimaryKey(record.getId());
				if(productDao.searchProductDes(temp.getBrand(), temp.getModel())!=null) {
					productDao.updateReduceProductCount(temp.getBrand(), temp.getModel(),temp.getCount());
					
					storeDao.updateByPrimaryKeySelective(record);
					
					}
				
			
		}else {
		//插入
		storeDao.insertSelective(record);
		}
		// if price 表里有当前品牌型号产品 
		//price表更新价格
		if(priceDao.selectPriceByModelAndBrand(record.getBrand(), record.getModel())!=null) {
			priceDao.updatePrice(record);
		}else {
		//else insert brand model price
			priceDao.insertSelective(record);
		}
		//如果product 存在当前品牌型号
		//count+store.count product表更新数量
		if(productDao.searchProductDes(record.getBrand(), record.getModel())!=null) {
			productDao.updateAddProductCount(record.getBrand(), record.getModel(),record.getCount());
		}else {
			//否则 插入当前品牌型号 in product
			productDao.insertByStore(record);
		}
		//格式化入库时间并返回
		
		record = storeDao.selectLastStoreDate(record.getBrand(), record.getModel());
		record.setDateString(ft.format(record.getDate()));
		return record;
	}
	
	@Override
	public Page getAllStoreRecordCount(Page page) {
		// TODO Auto-generated method stub
		page.setPageCount(storeDao.selectAllStoreRecordCount(page));
		return page;
	}
	
	@Override
	public Page getThisTypeStoreRecordCount(Page page) {
		// TODO Auto-generated method stub
		page.setPageCount(storeDao.selectThisTypeStoreRecordCount(page));
		return page;
	}
	
	@Override
	public Page searchStoreRecordCount(Page page) {
		ArrayList<String> arr = new ArrayList<String>();
		Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+|[a-zA-Z0-9\\-.*]+");
		Matcher m = p.matcher(page.getKey().trim());
        while ( m.find() ) {
            arr.add(m.group());
        }
      //此处有逻辑错误 
        if(arr.size()==1 && arr.get(0).matches("[\\u4e00-\\u9fa5]+") == false) {
        	page.setModel(arr.get(0));
        	page.setPageCount(storeDao.selectSearchedStoreRecordCountByModel(page));
        }else if(arr.size()==1 && arr.get(0).matches("[\\u4e00-\\u9fa5]+") == true) {
        	page.setBrand(arr.get(0));
        	page.setPageCount(storeDao.selectSearchedStoreRecordCountByBrand(page));
        }
        else {
        	page.setBrand(arr.get(0));
        	page.setModel(arr.get(1));
        	page.setPageCount(storeDao.selectSearchedStoreRecordCount(page));
        }
        
		return page;
	}
	@Override
	//删除记录
	public int removeRecordById(Integer id) {
		//找到记录
		Store temp = storeDao.selectByPrimaryKey(id);
		//在product表查找 此brand mdoel 记录 ，如果存在数量大于 temp 数量 ，count -temp.count 
		//否则 删除 product 记录
		if(productDao.searchProductDes(temp.getBrand(), temp.getModel()).getCount() < temp.getCount()) {
			return 0;
		}else if(productDao.searchProductDes(temp.getBrand(), temp.getModel()).getCount() > temp.getCount()) {
			productDao.updateReduceProductCount(temp.getBrand(), temp.getModel(),temp.getCount());
		}else {
			productDao.deleteByPrimaryKey(productDao.searchProductDes(temp.getBrand(), temp.getModel()).getId());
		}
		
		// 在store表 中查找 此brand mdoel 记录，如过只有一条记录 ，则删除 price表中记录  
		List<Store> list = storeDao.selectTwoLastStoreRecord(temp.getBrand(), temp.getModel());
		if(list.size() == 1) {
			priceDao.deleteByPrimaryKey(priceDao.selectPriceByModelAndBrand(temp.getBrand(), temp.getModel()).getId());
		}else {
			//否则 将第二条记录信息更新到price表
			priceDao.updatePrice(list.get(1));
		}
		//删除record
		return storeDao.deleteByPrimaryKey(id);
	}

}
