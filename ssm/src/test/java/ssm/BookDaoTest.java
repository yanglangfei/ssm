package ssm;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ssm.dao.BookDao;
import ssm.entity.Book;

public class BookDaoTest extends BaseTest{
	
	
	@Autowired
	BookDao bookDao;
	
	@Test
	public void testQueryById(){
		long bookId=100;
		Book book = bookDao.queryById(bookId);
		System.out.println(book);
	}
	
	@Test
	public void testQueryAll(){
		List<Book> books = bookDao.queryAll(0, 5);
		for(Book book : books){
			System.out.println("================");
			System.out.println(book);
		}
	}
	
	@Test
	public void testReduceNumber(){
		long bookId=100;
		int result = bookDao.reduceNum(bookId);
		System.out.println("result:"+result);
	}

}
