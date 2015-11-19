package list.br.com.cwi.list.exception;

public class EmptyListException extends RuntimeException {

	/**
	* 
	*/
	private static final long serialVersionUID = 1032151219149271701L;

	public EmptyListException(String msg) {
		super(msg);
	}

	public EmptyListException() {
		super();
	}
}
