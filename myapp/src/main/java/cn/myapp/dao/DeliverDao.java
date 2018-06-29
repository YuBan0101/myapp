package cn.myapp.dao;

import cn.myapp.model.Deliver;

public interface DeliverDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Deliver record);

    int insertSelective(Deliver record);

    Deliver selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Deliver record);

    int updateByPrimaryKey(Deliver record);
}