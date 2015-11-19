package sql.br.com.cwi.dao.exception;

public class NoRecordFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1672423496594335499L;
	
	public NoRecordFoundException(String msg) {
		super(msg);
	}

	public NoRecordFoundException() {
		super();
	}

}
