package ssm.exception;

/**
 * @author ���ʷ�
 * 
 *   ��治���쳣
 */
public class NoNumberException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoNumberException(String message) {
		super(message);
	}

	public NoNumberException(String message, Throwable cause) {
		super(message, cause);
	}
}
