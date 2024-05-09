package beauty.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalRestControllerAdvice {
	
	@ExceptionHandler(EntityNotFoundException.class)
	ResponseEntity<Void> handleEntityNotFoundException(EntityNotFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	ResponseEntity<Void> handleUserAlreadyExistException(UserAlreadyExistException e){
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@ExceptionHandler(ServiceAlreadyExistException.class)
	ResponseEntity<Void> handleServiceAlreadyExistException(ServiceAlreadyExistException e){
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	ResponseEntity<Void> handleIllegalArgumentException(IllegalArgumentException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

}
