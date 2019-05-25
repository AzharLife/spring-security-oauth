package com.security.authorization.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(value = CustomValidationException.class)
	public ResponseEntity<ErrorMessage> handleBaseException(Exception ex) {
		ex.printStackTrace();
		logger.error("Ops!", ex);
		CustomValidationException validationException = (CustomValidationException) ex;
		ErrorHandle errorHandle = validationException.getErrorCode();
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(errorHandle.getErrorCode());
		errorMessage.setMessage(errorHandle.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage,
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = AccessDeniedException.class)
	public ResponseEntity<ErrorMessage> handleAccessDeniedException(Exception ex) {
		// ex.printStackTrace();
		logger.error("Ops!", ex);
		ErrorHandle errorHandle = ErrorCode.SCF_VD_100;
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(errorHandle.getErrorCode());
		errorMessage.setMessage(errorHandle.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage,
				HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<ErrorMessage> handleRuntimeException(Exception ex) {
		ex.printStackTrace();
		logger.error("Ops!", ex);
		ErrorHandle errorHandle = ErrorCode.SCF_VD_0003;
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(errorHandle.getErrorCode());
		errorMessage.setMessage(errorHandle.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorMessage> handleException(Exception ex) {
		ex.printStackTrace();
		logger.error("Ops!", ex);
		ErrorHandle errorHandle = ErrorCode.SCF_VD_0003;
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(errorHandle.getErrorCode());
		errorMessage.setMessage(errorHandle.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
