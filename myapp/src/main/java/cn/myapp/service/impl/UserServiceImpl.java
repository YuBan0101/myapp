package cn.myapp.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.myapp.dao.UserDao;
import cn.myapp.model.User;
import cn.myapp.service.UserService;

@Service("UserServices")
public class UserServiceImpl implements UserService{

	 @Resource  
	    private UserDao userDao;  
	    
	    public User getUserById(int userId) {  
	        // TODO Auto-generated method stub  
	        return this.userDao.selectByPrimaryKey(userId);  
	    }  
	

}
