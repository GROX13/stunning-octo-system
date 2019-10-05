package me.giorgirokhadze.interview.gsg.controllers;

import me.giorgirokhadze.interview.gsg.controllers.model.RegistrationData;
import me.giorgirokhadze.interview.gsg.controllers.model.UserData;
import me.giorgirokhadze.interview.gsg.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {

	private final AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping("/user")
	public ResponseEntity getUser() {
		return ResponseEntity.ok().body(accountService.getLoggedInUser());
	}

	@PostMapping("/user/update")
	public ResponseEntity updateUser(@Valid @RequestBody UserData userData) {
		accountService.updateUser(userData);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/user/register")
	public ResponseEntity registerUser(@Valid @RequestBody RegistrationData registrationData) {
		accountService.registerUser(registrationData);
		return ResponseEntity.ok().build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
		final Map<String, String> errors = new HashMap<>();
		exception
				.getBindingResult()
				.getAllErrors()
				.forEach((error) -> {
					String fieldName = ((FieldError) error).getField();
					String errorMessage = error.getDefaultMessage();
					errors.put(fieldName, errorMessage);
				});
		return errors;
	}
}
