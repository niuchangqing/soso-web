package soso.zk.service;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

public class ZkInit {
	private static final String ZKSERVERS = "127.0.0.1:2181";

	public void init() {
		ZkClient client = new ZkClient(ZKSERVERS);

		if (!client.exists("/soso")) {
			client.createPersistent("/soso");
			System.err.println("created zookeeper path:/soso");
		}

		client.subscribeDataChanges("/soso", new IZkDataListener() {

			public void handleDataDeleted(String path) throws Exception {
				System.err.println("path[" + path + "] has deleted");

			}

			public void handleDataChange(String path, Object data) throws Exception {
				System.err.println("path[" + path + "] has changed,and data now is[" + data + "]");
			}
		});
	}
	
	public static void main(String[] args) {
		ZkClient client = new ZkClient(ZKSERVERS);
//		client.createPersistent("/soso");
//		client.createPersistent("/soso/test");
		client.createPersistent("/niucqisng/tedst/data","dd");
//		client.writeData("/soso/test/data", "change soso data");
//		client.writeData("/soso", "change soso data");
//		client.deleteRecursive("/soso");//("/soso");
	}
}
