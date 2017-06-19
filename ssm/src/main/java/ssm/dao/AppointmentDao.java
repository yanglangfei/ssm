package ssm.dao;

import org.apache.ibatis.annotations.Param;

import ssm.entity.Appointment;

public interface AppointmentDao {
	
	/**
	 * @param bookId
	 * @param studentId
	 * @return  ���Ԥ����Ϣ
	 */
	int insertAppoint(@Param("bookId") long bookId,@Param("studentId") long studentId);
	
	/**
	 * @param bookId
	 * @param studentId
	 * @return  ��ȡԤ����Ϣ
	 */
	Appointment queryByKeyWithBook(@Param("bookId") long bookId,@Param("studentId") long studentId);

}
