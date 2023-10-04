package tw.com.eeit168.products.shoppingCart.dao;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import tw.com.eeit168.products.shoppingCart.model.ShoppingCartBean;

public class ShoppingCartDaoImpl implements ShoppingCartDAO {
	
//	public String makeCookieValue(List<ShoppingCartBean> beans, ShoppingCartBean bean) {
//		JSONArray array = new JSONArray();
//		if(beans.size() <= 0) {
//			JSONObject object = new JSONObject();
//			object.put("key", ShoppingCartBean.class);
//			array.put(object);
//		} else {
//			ArrayList<Integer> list = new ArrayList<Integer>();
//			for(ShoppingCartBean item : beans) {
//				JSONObject object = new JSONObject();
//				object.put("key", ShoppingCartBean.class);
//				array.put(object);
//				list.add(item.getClass());
//			}
//		}
//	}

}
