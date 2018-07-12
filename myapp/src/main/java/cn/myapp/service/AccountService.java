package cn.myapp.service;

import java.util.List;

import cn.myapp.model.Account;
import cn.myapp.model.Page;

public interface AccountService {

	//获取账单信息
	public List<Account> getAccountInfo(Page record);
	
	//结账
    int changeStatus(Account record);
    
    //更新赊帐单
    int changeAccountInfo(Account record);
    
    //写入账本
    int addAccount(Account record);
    
    //删除选中账单
    int removeAccount(Integer id);
    
    //获取选中账单信息
    Account getSelectedAccount(Integer id);
    
  //获取选中账单信息 数量
    Page getSelectAccountInfoCount(Page record);

    //获取账单状态 byID
	public Account getSelectAccountInfoById(Account record);

	//获取关键字 账单信息
	public List<Account> getSearchAccountInfo(Page record);

	//获取关键字 账单信息 个数
	public Page getSearchAccountInfoCount(Page record);

	
}
