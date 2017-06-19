package ssm.entity;

import java.util.Date;

public class Appointment {

	
	private  long bookId;
	
	private long studentId;
	
	private  Date appointDate;
	
	private Book book;

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public Date getAppointDate() {
		return appointDate;
	}

	public void setAppointDate(Date appointDate) {
		this.appointDate = appointDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	@Override
	public String toString() {
		return "Appointment [bookId=" + bookId + ", studentId=" + studentId + ", appointDate=" + appointDate + "]";
	}
}
