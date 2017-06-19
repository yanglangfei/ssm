package ssm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ssm.dao.AppointmentDao;
import ssm.entity.Appointment;

public class AppointmentDaoTest extends BaseTest{
	
	@Autowired
	AppointmentDao appointmentDao;
	
	@Test
	public void testQueryAppointByKey(){
		long bookId=100;
		long studentId=102;
		Appointment appointment = appointmentDao.queryByKeyWithBook(bookId, studentId);
		System.out.println(appointment);
	}
	
	@Test
	public void testInsertAppointment(){
		long bookId=1001;
		long studentId=102;
		int result = appointmentDao.insertAppoint(bookId, studentId);
		System.out.println(result);
	}

}
