package br.com.cwi.list.exception;

public class EmptyListException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyListException(String msg) {
		super(msg);
	}

	public EmptyListException() {
		super();
	}
}
