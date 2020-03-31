package fabrizio.teste.upvote.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Post não encontrado")
public class PostNotFoundException extends Exception {
}
