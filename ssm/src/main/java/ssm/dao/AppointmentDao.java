package ssm.dao;

import org.apache.ibatis.annotations.Param;

import ssm.entity.Appointment;

public interface AppointmentDao {
	/**
	 * @param bookId
	 * @param studentId
	 * @return  添加预定信息
	 */
	int insertAppoint(@Param("bookId") long bookId,@Param("studentId") long studentId);
	/**
	 * @param bookId
	 * @param studentId
	 * @return  获取预定信息
	 */
	Appointment queryByKeyWithBook(@Param("bookId") long bookId,@Param("studentId") long studentId);

}