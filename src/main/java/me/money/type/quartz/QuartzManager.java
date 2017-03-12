package me.money.type.quartz;

import java.text.ParseException;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzManager {

	public static void addJob(String jobName) throws ParseException {
		try {
			SchedulerFactory sf = new	StdSchedulerFactory();
			Scheduler scheduler = sf.getScheduler();
			JobDetail detail = JobBuilder.newJob(SosoJob.class).withIdentity(jobName, "zu").build();

			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/2 * * * * ?");
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("triger", "zu").startNow()
					.withSchedule(scheduleBuilder).build();
			scheduler.scheduleJob(detail, trigger);
			
			if(!scheduler.isShutdown()){
				scheduler.start();
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		addJob("niu");
	}

}
