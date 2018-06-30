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
	public List<Product> getAllProduct() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		List<Product> list = new ArrayList<Product>();
		list = productDao.selectAllPrroduct();
		for(int i=0;i<list.size();i++) {
			list.get(i).setLastDeliverDate(ft.format((deliverDao.selectLastDeliverDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
			list.get(i).setLastStoreDate((ft.format(storeDao.selectLastStoreDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
		}
		return list;
	}
	@Override
	public List<String> getAllProductType() {
		
		List<Product> list = productDao.selectAllPrroductType();
		ArrayList<String> alist = new ArrayList<String>();
		for(int i = 0 ;i < list.size(); i++) {
			alist.add(list.get(i).getType());
		}
				
		return alist;
	}
	@Override
	public List<Product> getThisTypeProduct(String type) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		List<Product> list = new ArrayList<Product>();
		list = productDao.selectThisTypeProduct(type);
		for(int i=0;i<list.size();i++) {
			list.get(i).setLastDeliverDate(ft.format((deliverDao.selectLastDeliverDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
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
			list.get(i).setLastDeliverDate(ft.format((deliverDao.selectLastDeliverDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
			list.get(i).setLastStoreDate((ft.format(storeDao.selectLastStoreDate(list.get(i).getBrand(), list.get(i).getModel()).getDate())));
		}
		return list;
	}

	
	

}
