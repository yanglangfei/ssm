package ssm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ssm.dto.AppointExecution;
import ssm.service.BookService;
public class BookServiceImplTest extends BaseTest{
	
	@Autowired
	BookService bookService;
	
	
	@Test
	public void testAppoint() throws Exception{
		long bookId=1005;
		long studentId=109;
		AppointExecution execution = bookService.appoint(bookId, studentId);
		System.out.println(execution);
	}

}
