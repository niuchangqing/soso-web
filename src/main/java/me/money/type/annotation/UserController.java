package me.money.type.annotation;

import java.lang.reflect.Method;
import java.util.Map;

import com.google.common.collect.Maps;

import me.money.type.model.User;

public class UserController {

	@MyJson(returnCode = "getUserByid executed")
	public Map<String, Object> getUserByid(int id) {
		Map<String, Object> rs = Maps.newHashMap();
		User user = new User();
		user.setAge(18);
		user.setName("niucqing");
		rs.put("user", user);
		return rs;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		UserController u = new UserController();
		u.getUserByid(18);
		
		Class c = Class.forName("me.money.type.annotation.UserController");
		Method[] ms = c.getMethods();
		for (Method method : ms) {
			if(method.getName().equals("getUserByid")){
				
				MyJson j = method.getDeclaredAnnotation(MyJson.class);
				System.out.println(j.returnCode());
			}
		}
		
	}
}
