package ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ssm.entity.Book;

public interface BookDao {
	
	/**
	 * @param id
	 * @return  根据id获取图书信息
	 */
	Book queryById(long bookId);
	
	/**
	 * 两个参数需要添加注解，否则 batis 识别不了
	 * @param offset  起始位置
	 * @param limit   查询条数
	 * @return  获取全部图书信息
	 */
	List<Book>  queryAll(@Param("offset") int offset,@Param("limit") int limit);
	
	/**
	 * @param bookId
	 * @return  图书减少数量
	 */
	int reduceNum(long bookId);
	
	

}