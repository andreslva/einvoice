package itr.exception;

public class SAT_ITR_Exception extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID 	= 1L;

	public static final int NON_METADATA		= 2000;
	public static final int NON_METADATA_FIELD	= 3000;
	public static final int NO_ISR_TABLES		= 4000;
	public static final int NEG_NUM_OF_EXT_HRS	= 5000;
	public static final int WRONG_PERIOD_DGETI	= 6000;
	public static final int WRONG_FOLDER		= 7000;

	private int code;

	public SAT_ITR_Exception(int code) {
		super();
		this.code = code;
	}

	public SAT_ITR_Exception(String message, int code) {
		super(message);
		this.code = code;
	}

	public SAT_ITR_Exception() {
		// TODO Auto-generated constructor stub
	}

	public SAT_ITR_Exception(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SAT_ITR_Exception(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public SAT_ITR_Exception(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SAT_ITR_Exception(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public int getCode() {
		return code;
	}
}
