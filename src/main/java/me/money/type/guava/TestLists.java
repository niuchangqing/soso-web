package me.money.type.guava;

import java.util.List;

import com.google.common.collect.Lists;

public class TestLists {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list = Lists.newArrayList();
		for (int i = 0; i < 125; i++) {
			list.add(i);
		}
		
		List<List<Integer>> ss = Lists.partition(list, 20);
		for (List<Integer> list2 : ss) {
			System.out.println(list2.size());
		}
		
		List<Integer> li = Lists.asList(1, new Integer[]{4154});
		System.out.println(li);
		List<String> l = Lists.asList("a",new String[]{"b","d"} );
		System.out.println(l);
	}

}
