package ssm.task;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Schame {

	public static void main(String[] args) {
		JobDetail detail = JobBuilder.newJob(MyJob.class)
				.withIdentity("myDetail", "group1").build();
		SimpleTrigger trigger = TriggerBuilder.newTrigger().startNow()
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1))
				.build();
		SchedulerFactory factory = new StdSchedulerFactory();
		try {
			Scheduler scheduler = factory.getScheduler();
			scheduler.scheduleJob(detail, trigger);
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
