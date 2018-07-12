package cn.myapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.myapp.dao.AccountDao;
import cn.myapp.model.Account;
import cn.myapp.model.Page;
import cn.myapp.service.AccountService;


@Service("AccountService")
public class AccountServiceImpl implements AccountService {

	@Resource
	AccountDao accountDao;
	
	@Override
	//获取账单信息
	public List<Account> getAccountInfo(Page record) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd"); 
		List<Account> list = new ArrayList<Account>();
		record.setPageOffset();
		list = accountDao.selectAccountInfo(record);
		//更改status 1 为欠账  0 为已还账
		for(int i=0;i <list.size();i++) {
			if(list.get(i).getStatus() == 1) {
				list.get(i).setStatusString("欠账"+list.get(i).getMoney()+" 元");
			}else {
				list.get(i).setStatusString("已结清");
			}
			list.get(i).setDateString(ft.format(list.get(i).getDate()));
		}
		return list;
	}

	//修改status
	@Override
	public int changeStatus(Account record) {
		
		return accountDao.updateStatusById(record);
	}

	//修改账单信息
	@Override
	public int changeAccountInfo(Account record) {
		if(record.getStatusString() == "已支付") {
			record.setStatus((byte) 0);
		}else {
			record.setStatus((byte) 1);
		}
		record.setDate(new Date());
		return accountDao.updateByPrimaryKeySelective(record);
	}
	
	

	@Override
	//新增账单
	public int addAccount(Account record) {
		record.setStatus((byte) 1);
		record.setDate(new Date());
		return accountDao.insertSelective(record);
	}

	@Override
	//删除账单
	public int removeAccount(Integer id) {
		return accountDao.deleteByPrimaryKey(id);
	}

	
	
	@Override
	//获取选中账单
	public Account getSelectedAccount(Integer id) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd"); 
		Account account = accountDao.selectByPrimaryKey(id);
		if(account.getStatus() == 1) {
			account.setStatusString("欠账"+account.getMoney()+" 元");
		}else {
			account.setStatusString("已结清");
		}
		account.setDateString(ft.format(account.getDate()));
		
		return account;
	}

	
	
	//获取账单数量
	@Override
	public Page getSelectAccountInfoCount(Page record) {
		record.setPageCount(accountDao.selectAccountInfoCount());
		return record;
	}

	
	//获取账单状态 by id
	@Override
	public Account getSelectAccountInfoById(Account record) {
		return accountDao.selectByPrimaryKey(record.getId());
		
	}

	////获取关键字 账单信息
	@Override
	public List<Account> getSearchAccountInfo(Page record) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd"); 
		List<Account> list = new ArrayList<Account>();
		record.setPageOffset();
		list = accountDao.selectSearchAccountInfo(record);
		//更改status 1 为欠账  0 为已还账
		for(int i=0;i <list.size();i++) {
			if(list.get(i).getStatus() == 1) {
				list.get(i).setStatusString("欠账"+list.get(i).getMoney()+" 元");
			}else {
				list.get(i).setStatusString("已结清");
			}
			list.get(i).setDateString(ft.format(list.get(i).getDate()));
		}
		return list;
	}

	//获取关键字 账单信息 个数
	@Override
	public Page getSearchAccountInfoCount(Page record) {
		record.setPageCount(accountDao.selectSearchAccountInfoCount(record));
		return record;
	}
}
	
