package ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ssm.entity.Book;

public interface BookDao {
	
	/**
	 * @param id
	 * @return  ����id��ȡͼ����Ϣ
	 */
	Book queryById(long bookId);
	
	/**
	 * ����������Ҫ���ע�⣬���� batis ʶ����
	 * @param offset  ��ʼλ��
	 * @param limit   ��ѯ����
	 * @return  ��ȡȫ��ͼ����Ϣ
	 */
	List<Book>  queryAll(@Param("offset") int offset,@Param("limit") int limit);
	
	/**
	 * @param bookId
	 * @return  ͼ���������
	 */
	int reduceNum(long bookId);
	
	

}
