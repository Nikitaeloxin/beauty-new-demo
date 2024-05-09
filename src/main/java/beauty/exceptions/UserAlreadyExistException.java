package beauty.exceptions;

public class UserAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7165020753077449518L;
	
	public UserAlreadyExistException(String email){
		super("User with email " + email + "already exist");
	}

}
