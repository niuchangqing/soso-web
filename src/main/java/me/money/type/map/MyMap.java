package me.money.type.map;

public class MyMap<K, V> {
	private Object[][] pairs;

	private int index;

	public MyMap(int length) {
		pairs = new Object[length][2];
	}

	public void put(K key, V value) {
		if (index > pairs.length)
			throw new ArrayIndexOutOfBoundsException();

		pairs[index++] = new Object[] { key, value };
	}

	public V get(K key) {
		for (Object[] o : pairs) {
			if (o[0] == key)
				return (V) o[1];
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String line = "";
		for (Object[] o : pairs) {
			sb.append(line);
			sb.append(o[0].toString());
			sb.append(":");
			sb.append(o[1].toString());
			line = ";";
		}
		return sb.toString();
	}
}
