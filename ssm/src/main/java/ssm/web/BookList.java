package ssm.web;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ssm.dto.AppointExecution;
import ssm.dto.Result;
import ssm.entity.Book;
import ssm.enums.AppointStateEnum;
import ssm.exception.NoNumberException;
import ssm.exception.RepeatAppointException;
import ssm.service.BookService;

@Controller
@RequestMapping("/book")
public class BookList {

	@Autowired
	BookService bookService;

	@RequestMapping(value = "/{bookId}/detail", method = RequestMethod.GET)
	public String queryBooks(@PathVariable("bookId") Long bookId, Model model) {
		if (bookId == null) {
			return "redirect:/book/list";
		}
		Book book = bookService.getById(bookId);
		if (book == null) {
			return "forward:/book/list";
		}
		model.addAttribute("book", book);
		return "detail";
	}

	@RequestMapping(value = "/list")
	public String queryAll(HttpServletResponse resp, Model model) {
		List<Book> list = bookService.getList();
		model.addAttribute("books", list);
		return "list";
	}
	
	@RequestMapping(value="/{bookId}/appoint",method=RequestMethod.POST,produces="application/json; charset=utf-8")
	private Result<AppointExecution> appoint(@PathVariable("bookId") Long bookId,@PathVariable("studentId") Long studentId){
		if(studentId==null||studentId.equals("")){
			return new Result<>(false,"学号不能为空");
		}
		AppointExecution execution=null;
		try {
			execution=bookService.appoint(bookId, studentId);
		} catch (NoNumberException e) {
			execution=new AppointExecution(bookId, AppointStateEnum.NO_NUMBER);
		}catch (RepeatAppointException e) {
			execution=new AppointExecution(bookId,AppointStateEnum.REPEAT_APPOINT);
		}catch (Exception e) {
			execution=new AppointExecution(bookId,AppointStateEnum.INNER_ERROR);
		}
		return new Result<>(true, execution);
	}

}
