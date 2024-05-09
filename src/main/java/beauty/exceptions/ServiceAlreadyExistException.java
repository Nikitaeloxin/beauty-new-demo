package beauty.exceptions;

public class ServiceAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1431542636271052002L;
	
	public ServiceAlreadyExistException(String serviceName,Long saloonId) {
		super("Saloon with id " + saloonId + " already has service with name " + serviceName);
	}

}
