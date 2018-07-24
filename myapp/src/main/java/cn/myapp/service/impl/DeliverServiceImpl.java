package cn.myapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.myapp.dao.DeliverDao;
import cn.myapp.dao.PriceDao;
import cn.myapp.dao.ProductDao;
import cn.myapp.dao.StoreDao;
import cn.myapp.model.Jsdata;
import cn.myapp.model.Deliver;
import cn.myapp.model.Page;
import cn.myapp.service.DeliverService;

@Service("DeliverService")
public class DeliverServiceImpl implements DeliverService{

	@Resource
	private DeliverDao deliverDao;
	@Resource
	private ProductDao productDao;
	@Resource
	private StoreDao storeDao;
	@Resource
	private PriceDao priceDao;
	
	@Override
	public int getThisMonthDeliverCount() {
		
		return deliverDao.selectThisMonthDeliverCount();
	}
	@Override
	public double getThisMonthDeliverPrice() {
		
		return deliverDao.selectThisMonthDeliverPrice();
	}
	@Override
	//获得净利润  
	//肥来了 ，store问题
	public double getThisMonthProfit() {
		
		return deliverDao.selectThisMonthProfit();
	}
	@Override
	@Transactional
	//插入一条数据deliver 并在product里对count -delivercount;
	public Deliver getDeliverRecordAfterAdd(Deliver record) {
		
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		record.setDate(new Date());
		//插入
		deliverDao.insertSelective(record);
		//count--
		
		productDao.updateReduceProductCount(record.getBrand(), record.getModel(),record.getCount());
		record = deliverDao.selectLastDeliverDate(record.getBrand(), record.getModel());
		record.setDateString(ft.format(record.getDate()));
		return record;
	}
	@Override
	public List<Deliver> getDeliverRecord() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		List<Deliver> list = new ArrayList<Deliver>();
		list = deliverDao.selectDeliverRecord();
		
		for(int i=0;i<list.size();i++) {
			list.get(i).setType(productDao.searchProductDes(list.get(i).getBrand(), list.get(i).getModel()).getType());
			list.get(i).setDateString(ft.format(list.get(i).getDate()));
		}
		return list;
	}
	//获取所有入库记录
	@Override
	public List<Deliver> getAllDeliverRecord(Page page) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		List<Deliver> list = new ArrayList<Deliver>();
		page.setPageOffset();
		list = deliverDao.selectAllDeliverRecord(page);
		for(int i=0;i<list.size();i++) {
			list.get(i).setDateString(ft.format(list.get(i).getDate()));
			list.get(i).setType(productDao.searchProductDes(list.get(i).getBrand(), list.get(i).getModel()).getType());
			list.get(i).setSprice(priceDao.selectPriceByModelAndBrand(list.get(i).getBrand(), list.get(i).getModel()).getPrice());
		}
		return list;
	}
	
	//sql 大法好  指定type 出库记录
	@Override
	public List<Deliver> getThisTypeDeliverRecord(Page page) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		List<Deliver> list = new ArrayList<Deliver>();
		page.setPageOffset();
		list = deliverDao.selectThisTypeDeliverRecord(page);
		for(int i=0;i<list.size();i++) {
			list.get(i).setDateString(ft.format(list.get(i).getDate()));
			list.get(i).setType(page.getType());
			list.get(i).setSprice(priceDao.selectPriceByModelAndBrand(list.get(i).getBrand(), list.get(i).getModel()).getPrice());
			
		}
		return list;
	}
	
	//查找关键字 出库记录
	@Override
	public List<Deliver> searchDeliverRecord(Page page) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		List<Deliver> list = new ArrayList<Deliver>();
		ArrayList<String> arr = new ArrayList<String>();
		Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+|[a-zA-Z0-9\\-]+");
		Matcher m = p.matcher(page.getKey().trim());
        while ( m.find() ) {
            arr.add(m.group());
        }
        page.setPageOffset();
      //此处有逻辑错误 
        if(arr.size()==1 && arr.get(0).matches("[\\u4e00-\\u9fa5]+") == false) {
        	page.setModel(arr.get(0));
        	list = deliverDao.searchDeliverRecordByModel(page);
        }else if(arr.size()==1 && arr.get(0).matches("[\\u4e00-\\u9fa5]+") == true) {
        	page.setBrand(arr.get(0));
        	list = deliverDao.searchDeliverRecordByBrand(page);
        }
        else {
        	page.setBrand(arr.get(0));
        	page.setModel(arr.get(1));
        	list = deliverDao.searchDeliverRecord(page);
        }
        for(int i=0;i<list.size();i++) {
        	if(deliverDao.selectLastDeliverDate(list.get(i).getBrand(), list.get(i).getModel())== null ) {
				list.get(i).setDateString("暂无出库时间信息");
			}else {
			list.get(i).setDateString(ft.format((deliverDao.selectLastDeliverDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
			list.get(i).setType(productDao.searchProductDes(list.get(i).getBrand(), list.get(i).getModel()).getType());
			}
			
		}
		return list;
	}
	
	
	//查找关键字 出库记录 数量
	@Override
	public Page searchDeliverRecordCount(Page page) {
		ArrayList<String> arr = new ArrayList<String>();
		Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+|[a-zA-Z0-9\\-]+");
		Matcher m = p.matcher(page.getKey().trim());
        while ( m.find() ) {
            arr.add(m.group());
        }
      //此处有逻辑错误 
        if(arr.size()==1 && arr.get(0).matches("[\\u4e00-\\u9fa5]+") == false) {
        	page.setModel(arr.get(0));
        	page.setPageCount(deliverDao.searchDeliverRecordByModelCount(page));
        }else if(arr.size()==1 && arr.get(0).matches("[\\u4e00-\\u9fa5]+") == true) {
        	page.setBrand(arr.get(0));
        	page.setPageCount(deliverDao.searchDeliverRecordByBrandCount(page));
        }
        else {
        	page.setBrand(arr.get(0));
        	page.setModel(arr.get(1));
        	page.setPageCount(deliverDao.searchDeliverRecordCount(page));
        }
		return page;
	}
	
	//获取所有出库信息 数量
	@Override
	public Page getAllDeliverRecordCount(Page page) {
		page.setPageCount(deliverDao.selectAllDeliverRecordCount(page));
		return page;
	}
	@Override
	public Page getThisTypeDeliverRecordCount(Page page) {
		page.setPageCount(deliverDao.selectThisTypeDeliverRecordCount(page));
		return page;
	}
	
	@Override
	//获取一年中的月份 销售金额
	public Map<String,Object> getMonthSalesMoney() {
		List<Jsdata> list = new ArrayList<Jsdata>();
		list = deliverDao.selectMonthSalesMoney();
		ArrayList<String> label = new ArrayList<String>();
		List<Object> datasets = new ArrayList<Object>();
		ArrayList<Double> data1 = new ArrayList<Double>();
		ArrayList<Double> data2 = new ArrayList<Double>();
		for(int i =1,j=0 ;i< 12 && j <list.size();i++) {
			if(list.get(j).getMonth() == i) {
			label.add(list.get(j).getMonth()+" 月");
			data1.add(i-1,list.get(j).getMoney());
			data2.add(i-1,list.get(j).getSalesMoney());
			j++;
			}else {
				label.add(i+" 月");
				data1.add(i-1,0.0);
				data2.add(i-1,0.0);
			}
			
		}
		datasets.add(data1);
		datasets.add(data2);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("labels", label);
		map.put("datasets", datasets);
		return map;
	}
	
	
}


