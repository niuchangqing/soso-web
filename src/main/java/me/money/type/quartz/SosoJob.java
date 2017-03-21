package me.money.type.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import me.money.type.log.Log;

public class SosoJob implements Job {

	private static int i = 0;

	@Override
	public void execute(JobExecutionContext job) throws JobExecutionException {
		i += 1;
		Log.log(i, this.toString());// toString结果每次不一样，说明每次都会创建新的实例
	}

}
