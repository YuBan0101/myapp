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

import cn.myapp.dao.DeliverDao;
import cn.myapp.dao.ProductDao;
import cn.myapp.dao.StoreDao;
import cn.myapp.model.Deliver;
import cn.myapp.model.Product;
import cn.myapp.service.DeliverService;

@Service("DeliverService")
public class DeliverServiceImpl implements DeliverService{

	@Resource
	private DeliverDao deliverDao;
	@Resource
	private ProductDao productDao;
	@Resource
	private StoreDao storeDao;
	
	@Override
	public int getThisMonthDeliverCount() {
		
		return deliverDao.selectThisMonthDeliverCount();
	}
	@Override
	public double getThisMonthDeliverPrice() {
		
		return deliverDao.selectThisMonthDeliverPrice();
	}
	@Override
	public double getThisMonthProfit() {
		
		return deliverDao.selectThisMonthProfit();
	}
	@Override
	@Transactional
	//插入一条数据deliver 并在product里对count -1;
	public Deliver getDeliverRecordOne(Deliver record) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		record.setDate(new Date());
		//插入
		deliverDao.insertSelective(record);
		//count--
		productDao.updateProductCount(record.getBrand(), record.getModel());
		record = deliverDao.selectLastDeliverDate(record.getBrand(), record.getModel());
		record.setDateString(ft.format(record.getDate()));
		return record;
	}
	@Override
	public List<Deliver> getDeliverRecord() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		List<Deliver> list = new ArrayList<Deliver>();
		list = deliverDao.selectDeliverRecord();
		for(int i=0;i<list.size();i++) {
			list.get(i).setDateString(ft.format(list.get(i).getDate()));
		}
		return list;
	}
	@Override
	public List<Deliver> getAllDeliverRecord() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		List<Deliver> list = new ArrayList<Deliver>();
		list = deliverDao.selectAllDeliverRecord();
		for(int i=0;i<list.size();i++) {
			list.get(i).setDateString(ft.format(list.get(i).getDate()));
			list.get(i).setType(productDao.searchProductDes(list.get(i).getBrand(), list.get(i).getModel()).getType());
			list.get(i).setSprice(storeDao.searchStoreRecordDes(list.get(i).getBrand(), list.get(i).getModel()).getPrice());
		}
		return list;
	}
	@Override
	public List<Deliver> getThisTypeDeliverRecord(String type) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		List<Deliver> list = new ArrayList<Deliver>();
		List<Product> list1 = new ArrayList<Product>();
		list1 = productDao.selectThisTypeProduct(type);
		for(int i=0;i<list1.size();i++) {
			
			
		}
		return list;
	}
	@Override
	public List<Deliver> searchDeliverRecord(String key) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		List<Deliver> list = new ArrayList<Deliver>();
		ArrayList<String> arr = new ArrayList<String>();
		Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+|\\d+");
		Matcher m = p.matcher(key.trim());
        while ( m.find() ) {
            arr.add(m.group());
        }
      //此处有逻辑错误 
        if(arr.size()==1 && arr.get(0).matches("[\\u4e00-\\u9fa5]+") == false) {
        	list = deliverDao.searchDeliverRecordByModel(arr.get(0));
        }else if(arr.size()==1 && arr.get(0).matches("[\\u4e00-\\u9fa5]+") == true) {
        	list = deliverDao.searchDeliverRecordByBrand(arr.get(0));
        }
        else {
        	list = deliverDao.searchDeliverRecord(arr.get(0), arr.get(1));
        }
        for(int i=0;i<list.size();i++) {
        	if(deliverDao.selectLastDeliverDate(list.get(i).getBrand(), list.get(i).getModel())== null ) {
				list.get(i).setDateString("暂无出库时间信息");
			}else {
			list.get(i).setDateString(ft.format((deliverDao.selectLastDeliverDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
			}
			
		}
		return list;
	}
	
}


