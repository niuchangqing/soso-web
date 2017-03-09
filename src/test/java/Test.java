import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<Long> anchorIds = new ArrayList<Long>();
		anchorIds.add(123L);
		
		anchorIds.stream().forEach(e->System.out.println(e));
		
	}

}
