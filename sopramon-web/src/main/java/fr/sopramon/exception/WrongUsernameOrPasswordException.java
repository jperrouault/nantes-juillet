package fr.sopramon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="Le nom d'utilisateur ou le mot de passe est invalide.")
public class WrongUsernameOrPasswordException extends RuntimeException {
	private static final long serialVersionUID = 1L;
}
