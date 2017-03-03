package me.money.type.callback;

public class Employer {
	private BossCallBack callback;

	public Employer(BossCallBack callback) {
		// TODO Auto-generated constructor stub
		this.callback = callback;
	}

	public void doSome() {
		int c = 0;
		while (c < 1000) {
			c++;
			System.err.println("do some " + c);
		}
		new BossCallBack() {
			
			@Override
			public void execute() {
				// TODO Auto-generated method stub
				
			}
		};
		callback.execute();
	}
}
