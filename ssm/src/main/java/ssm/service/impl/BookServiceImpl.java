package ssm.service.impl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssm.dao.AppointmentDao;
import ssm.dao.BookDao;
import ssm.dto.AppointExecution;
import ssm.entity.Appointment;
import ssm.entity.Book;
import ssm.enums.AppointStateEnum;
import ssm.exception.AppointException;
import ssm.exception.NoNumberException;
import ssm.exception.RepeatAppointException;
import ssm.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// ע��Service����
	@Autowired
	private BookDao bookDao;

	@Autowired
	private AppointmentDao appointmentDao;


	@Override
	public Book getById(long bookId) {
		return bookDao.queryById(bookId);
	}

	@Override
	public List<Book> getList() {
		return bookDao.queryAll(0, 1000);
	}

	@Override
	@Transactional
	/**
	 * ʹ��ע��������񷽷����ŵ㣺 1.�����ŶӴ��һ��Լ������ȷ��ע���񷽷��ı�̷��
	 * 2.��֤���񷽷���ִ��ʱ�価���̣ܶ���Ҫ�����������������RPC/HTTP������߰��뵽���񷽷��ⲿ
	 * 3.�������еķ�������Ҫ������ֻ��һ���޸Ĳ�����ֻ����������Ҫ�������
	 */
	public AppointExecution appoint(long bookId, long studentId) {
		try {
			// �����
			int update = bookDao.reduceNum(bookId);
			if (update <= 0) {// ��治��
				//return new AppointExecution(bookId, AppointStateEnum.NO_NUMBER);//����д��				
				throw new NoNumberException("no number");
			} else {
				// ִ��ԤԼ����
				int insert = appointmentDao.insertAppoint(bookId, studentId);
				if (insert <= 0) {// �ظ�ԤԼ
					//return new AppointExecution(bookId, AppointStateEnum.REPEAT_APPOINT);//����д��
					throw new RepeatAppointException("repeat appoint");
				} else {// ԤԼ�ɹ�
					Appointment appointment = appointmentDao.queryByKeyWithBook(bookId, studentId);
					return new AppointExecution(bookId, AppointStateEnum.SUCCESS, appointment);
				}
			}
		// Ҫ����catch Exception�쳣ǰ��catchס���׳�����Ȼ�Զ�����쳣Ҳ�ᱻת��ΪAppointException�����¿��Ʋ��޷�����ʶ�����ĸ��쳣
		} catch (NoNumberException e1) {
			throw e1;
		} catch (RepeatAppointException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// ���б������쳣ת��Ϊ�������쳣
			//return new AppointExecution(bookId, AppointStateEnum.INNER_ERROR);//����д��
			throw new AppointException("appoint inner error:" + e.getMessage());
		}
	}

}