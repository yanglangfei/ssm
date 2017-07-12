package ssm.task;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CoreTrigger;
/*
 * 字段	 	允许值	 	                 允许的特殊字符
       秒	 	0-59	 	        , - * /
       分	 	0-59	 	        , - * /
      小时	 	0-23	 	        , - * /
      日期	 	1-31	 	        , - * ? / L W C
      月份	 	1-12 或者 JAN-DEC	 	, - * /
      星期	 	1-7 或者 SUN-SAT	 	, - * ? / L C #
      年（可选）	 留空, 1970-2099	 	, - * /
 * 
 *  1.'*'  表示所有时间  每分钟  每秒
 *  2.'?'  只在日期和星期中使用   忽略不设置
 *  3.'-'  用来指定一个范围
 *  4.','  用来指定多个值 用分号隔开
 *  5.'/'  执行周期     5/10   从第五秒开始执行 ，间隔10秒执行
 *  6.'L'  使用在月和星期中，月或者星期的最后几天   6L 每月最后六天  
 *  7.'W'  使用在月份中  表示工作日     1LW  每月的最后一个工作日
 *  8.'#'  使用在星期中       2#3  每月的第3个星期二
 *  9.'C'  使用在星期和月份中
 */

/**
 * @Method Schame
 * @Description
 * @author 杨朗飞
 */
public class Schame {
	
	/*
	 * 每天 12：:3 执行
	 */
	private static String cronExpression = "0 30 12 * * ? *";
	
	/**
	 *  每月的8号 凌晨执行
	 */
	private static String cronExpression2="0 0 24 8 * * ? *";
	
	/**
	 *  每年的10月1号凌晨执行
	 */
	private static String cronExpression3="0 0 24 1 10 * ? *";
	
	/*
	 * 每年的11月11日11时11分11秒 、12月11日11时11分11秒 执行
	 */
	private static String cronExpression4="11 11 11 11 11,12 ? *";
	

	public static void main(String[] args) {
		JobDetail detail = JobBuilder.newJob(MyJob.class)
				.withIdentity("myDetail", "group1").build();
		
		JobDetail detail2=JobBuilder.newJob(MyJob.class)
				.withIdentity("myDetail2", "group2")
				.build();
		
		SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
				.startNow()
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(2))
				.build();

		CoreTrigger coreTrigger = (CoreTrigger) TriggerBuilder.newTrigger()
				.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
				.build();
		SchedulerFactory factory = new StdSchedulerFactory();
		try {
			Scheduler scheduler = factory.getScheduler();
			scheduler.scheduleJob(detail, simpleTrigger);
			scheduler.scheduleJob(detail2, coreTrigger);
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}