package com.oracle.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.oracle.entity.Shop_User;
import com.oracle.entity.shop_cart;

import cn.lds.C3P0.C3P0Utils;

public class CartDao implements com.oracle.interf.dao.CartDao{

	@Override
	public List<shop_cart> cartAll(int cpage, int count) throws SQLException {
		int cp =0;
		if(cpage==1) {
			cp=0;
		}else {
			cp=(cpage-1)*5;
		}
		String sql ="select * from shop_cart limit ?,?";
		QueryRunner runner =new QueryRunner(C3P0Utils.getDataSource());
	
		return runner.query( sql,new BeanListHandler<shop_cart> (shop_cart.class),cp,count);
	}

	@Override
	public void cartAdd(shop_cart cate) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cartUpdate(shop_cart cate) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cartDel(int cid) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public  int[] totalPage() throws SQLException {
		int arr[]= {0,1};
		
		String sql ="select count(*) from shop_cart";
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		Long no= (Long) runner.query(sql, new ScalarHandler());
		arr[0]=no.intValue();
		if(no.intValue()%5!=0) {
			arr[1]=no.intValue()/5+1;
		}else {
			arr[1]=no.intValue()/5;
		}
		return arr;
	}

}
