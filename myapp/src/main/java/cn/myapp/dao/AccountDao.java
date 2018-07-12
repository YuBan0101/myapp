package cn.myapp.dao;

import java.util.List;

import cn.myapp.model.Account;
import cn.myapp.model.Page;

public interface AccountDao {
	
	 // 删除选中账单
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    //写入账本
    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

  //更新赊帐单
    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
    
    //结账
    int updateStatusById(Account record);
    

    //获取账单信息
    List<Account> selectAccountInfo(Page record);
    
    //获取账单信息 数量
    int selectAccountInfoCount();
    
    //获取关键字 账单信息
    List<Account> selectSearchAccountInfo(Page record);
    
    //获取关键字 账单信息 数量
    int selectSearchAccountInfoCount(Page record);
    
}