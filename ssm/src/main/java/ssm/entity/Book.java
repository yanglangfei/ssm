package ssm.entity;

/**
 * @author —Ó¿ ∑…
 * 
 *   Õº È
 */
public class Book {
	
	private long bookId;
	
	private String name;
	
	private int number;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}


	@Override
	public String toString() {
		 return "Book [bookId=" + bookId + ", name=" + name + ", number=" + number + "]";
	}
	
	
}
