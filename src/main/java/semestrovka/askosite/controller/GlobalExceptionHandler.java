package semestrovka.askosite.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import semestrovka.askosite.exceptions.EmailAlreadyExistsException;
import semestrovka.askosite.exceptions.NicknameAlreadyExistsException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NicknameAlreadyExistsException.class)
    public String handleNicknameExists(
            NicknameAlreadyExistsException ex,
            Model model,
            HttpServletRequest request
    ) {
        model.addAttribute("title", "Ошибка регистрации");
        model.addAttribute("message", "Пользователь с таким ником уже существует!");
        model.addAttribute("path", request.getRequestURI());

        return "error/custom-error";
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public String handleEmailExists(
            EmailAlreadyExistsException ex,
            Model model,
            HttpServletRequest request
    ) {
        model.addAttribute("title", "Ошибка регистрации");
        model.addAttribute("message", "Эта почта уже кем-то используется");
        model.addAttribute("path", request.getRequestURI());

        return "error/custom-error";
    }

    @ExceptionHandler(Exception.class)
    public String handleOtherExceptions(
            Exception ex,
            Model model,
            HttpServletRequest request
    ) {
        model.addAttribute("title", "Internal server error");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("path", request.getRequestURI());

        return "error/custom-error";
    }
}
