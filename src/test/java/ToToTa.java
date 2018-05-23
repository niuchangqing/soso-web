import java.util.HashMap;
import java.util.Map;

import me.money.type.redis.LockTest;

public class Test {

	public static void main(String[] args) {
		Map<String, Object> map  = new HashMap<String,Object>();
		LockTest.getMsg(map, 122);
		System.out.println(map);
	}
	
	
}
