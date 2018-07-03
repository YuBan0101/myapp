package cn.myapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.myapp.dao.DeliverDao;
import cn.myapp.dao.ProductDao;
import cn.myapp.dao.StoreDao;
import cn.myapp.model.Page;
import cn.myapp.model.Product;
import cn.myapp.service.ProductService;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {

	@Resource
	private ProductDao productDao;
	@Resource
    private DeliverDao deliverDao;
	@Resource
    private StoreDao storeDao;
	@Override
	//获取所有产品
	public List<Product> getAllProduct(Page page) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		List<Product> list = new ArrayList<Product>();
		page.setPageOffset();
		list = productDao.selectAllProduct(page);
		for(int i=0;i<list.size();i++) {
			//if 产品没有出库  
			if(deliverDao.selectLastDeliverDate(list.get(i).getBrand(), list.get(i).getModel())== null ) {
				list.get(i).setLastDeliverDate("暂无出库时间信息");
			}else {
			list.get(i).setLastDeliverDate(ft.format((deliverDao.selectLastDeliverDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
			}
			list.get(i).setLastStoreDate((ft.format(storeDao.selectLastStoreDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
		}
		return list;
	}
	
	
	
	@Override
	public List<String> getAllProductType() {
		
		List<Product> list = productDao.selectAllProductType();
		ArrayList<String> alist = new ArrayList<String>();
		for(int i = 0 ;i < list.size(); i++) {
			alist.add(list.get(i).getType());
		}
				
		return alist;
	}
	@Override
	public List<Product> getThisTypeProduct(Page page) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		List<Product> list = new ArrayList<Product>();
		page.setPageOffset();
		list = productDao.selectThisTypeProduct(page);
		for(int i=0;i<list.size();i++) {
			//此处如果deliver 和Store 表中没有Date 报错 ，修改成？表达式试试
			//list.get(i).setLastDeliverDate(ft.format((deliverDao.selectLastDeliverDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
			//list.get(i).setLastStoreDate((ft.format(storeDao.selectLastStoreDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
		//} 出库时间空值修正  入库时间必有无需校验
			if(deliverDao.selectLastDeliverDate(list.get(i).getBrand(), list.get(i).getModel())== null ) {
				list.get(i).setLastDeliverDate("暂无出库时间信息");
			}else {
			list.get(i).setLastDeliverDate(ft.format((deliverDao.selectLastDeliverDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
			}
			list.get(i).setLastStoreDate((ft.format(storeDao.selectLastStoreDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
		}
		return list;
	}
	@Override
	public List<Product> searchProduct(String key) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		List<Product> list = new ArrayList<Product>();
		ArrayList<String> arr = new ArrayList<String>();
		Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+|\\d+");
		Matcher m = p.matcher(key.trim());
        while ( m.find() ) {
            arr.add(m.group());
        }
      //此处有逻辑错误 
        if(arr.size()==1 && arr.get(0).matches("[\\u4e00-\\u9fa5]+") == false) {
        	list = productDao.searchProductByModel(arr.get(0));
        }else if(arr.size()==1 && arr.get(0).matches("[\\u4e00-\\u9fa5]+") == true) {
        	list = productDao.searchProductByBrand(arr.get(0));
        }
        else {
        	list = productDao.searchProduct(arr.get(0), arr.get(1));
        }
        for(int i=0;i<list.size();i++) {
        	if(deliverDao.selectLastDeliverDate(list.get(i).getBrand(), list.get(i).getModel())== null ) {
				list.get(i).setLastDeliverDate("暂无出库时间信息");
			}else {
			list.get(i).setLastDeliverDate(ft.format((deliverDao.selectLastDeliverDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
			}
			list.get(i).setLastStoreDate((ft.format(storeDao.selectLastStoreDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
		}
		return list;
	}
	@Override
	public List<Product> getAllShortSupplyProduct() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		List<Product> list = new ArrayList<Product>();
		list = productDao.selectAllShortSupplyProduct();
		for(int i=0;i<list.size();i++) {
			if(deliverDao.selectLastDeliverDate(list.get(i).getBrand(), list.get(i).getModel())== null ) {
				list.get(i).setLastDeliverDate("暂无出库时间信息");
			}else {
			list.get(i).setLastDeliverDate(ft.format((deliverDao.selectLastDeliverDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
			}
			list.get(i).setLastStoreDate((ft.format(storeDao.selectLastStoreDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
		}
		return list;
	}
	@Override
	public List<Product> getThisTypeShotSupplyProduct(String type) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		List<Product> list = new ArrayList<Product>();
		list = productDao.selectThisTypeShortSupplyProduct(type);
		for(int i=0;i<list.size();i++) {
			if(deliverDao.selectLastDeliverDate(list.get(i).getBrand(), list.get(i).getModel())== null ) {
				list.get(i).setLastDeliverDate("暂无出库时间信息");
			}else {
			list.get(i).setLastDeliverDate(ft.format((deliverDao.selectLastDeliverDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
			}
			list.get(i).setLastStoreDate((ft.format(storeDao.selectLastStoreDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
		}
		return list;
	}

	@Override
	//获取全部product 个数 放入page 对象
	public Page getAllProductCount(Page page) {
		page.setPageCount(productDao.selectAllProductCount());
		return page;
	}


	@Override
	//获取type product 个数 放入page 对象
	public Page getThisTypeProductCount(Page page) {
		page.setPageCount(productDao.selectThisTypeProductCount(page));
		return page;
	}

	
	

}
