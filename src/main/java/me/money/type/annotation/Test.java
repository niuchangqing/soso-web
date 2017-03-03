package me.money.type.annotation;

import java.lang.reflect.Field;

public class Test {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Worker w = new Worker();
		w.setName("niucqing");
		w.setSex("男");
		w.setWorkinfo("dd");
		Test t = new Test();
		t.getInfos(w,w.getClass());
	}

	public void getInfos(Object o,Class<?> clazz) throws IllegalArgumentException, IllegalAccessException {

		Field[] fs = clazz.getDeclaredFields();
		for (Field f : fs) {
			f.setAccessible(true);  
			if (f.isAnnotationPresent(UserName.class)) {
				UserName n = f.getAnnotation(UserName.class);
				String v = n.value();
				String name = f.getName();
				v = (String) f.get(o);
				System.out.println(name + v);
			}

			if (f.isAnnotationPresent(UserSex.class)) {
				UserSex s = f.getAnnotation(UserSex.class);
				String v = s.value();
				String sex = f.getName();
				System.out.println(sex + v);
			}

			if (f.isAnnotationPresent(WorkAddress.class)) {
				WorkAddress w = f.getAnnotation(WorkAddress.class);
				String v = w.address();
				int fl = w.floor();
				String info = f.getName();
				System.out.println(info + v + "--" + fl);
			}
		}
	}
}
