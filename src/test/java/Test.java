import java.util.Random;

import org.joda.time.DateTime;

public class Test {

	public static void main(String[] args) {

		String anchors = "361675,361676,361678,361680,369656,369657,369659,369660,369661";
		String date = DateTime.now().toString("yyyy-MM-dd");
		String[] s = anchors.split(",");
		StringBuilder sb = new StringBuilder("sadd qmeng-anchors-list ");
		for (String id : s) {
			long num = new Random().nextInt(10) + 5;
			sb.append(id).append(" ");
			System.out.println("zincrby qm-rt-rank-" + date + " " + num + " " + id);
		}

		System.err.println(sb);
	}

}
