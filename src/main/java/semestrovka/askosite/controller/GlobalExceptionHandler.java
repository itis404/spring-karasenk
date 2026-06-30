package semestrovka.askosite.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import semestrovka.askosite.exceptions.*;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(NicknameAlreadyExistsException ex, Model model){
        log.error("AccessDeniedException: {}", ex.getMessage());
        model.addAttribute("errorMessage", ex.getMessage());
        return "error_page";
    }

    @ExceptionHandler(NicknameAlreadyExistsException.class)
    public String handleNicknameAlreadyExistsException(NicknameAlreadyExistsException ex, Model model) {
        log.error("NicknameAlreadyExistsException: {}", ex.getMessage());
        model.addAttribute("error", "Пользователь с таким ником уже существует!");
        return "register";
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public String handleEmailAlreadyExistsException(EmailAlreadyExistsException ex, Model model) {
        model.addAttribute("error", "Эта почта уже кем-то используется");
        log.error("EmailAlreadyExistsException: {}", ex.getMessage());
        return "register";
    }

    @ExceptionHandler(RegisterInvalidException.class)
    public String handleRegisterInvalidException(RegisterInvalidException ex, Model model){
        model.addAttribute("error", ex.getMessage());
        log.error("RegisterInvalidException: {}", ex.getMessage());
        return "register";
    }
    @ExceptionHandler(AskAlreadyExistsException.class)
    public String handleAskAlreadyExistsException(AskAlreadyExistsException ex, Model model) {
        model.addAttribute("error", "Аск с такой ссылкой уже существует");
        model.addAttribute("contentPage", "/WEB-INF/views/create_ask.jsp");
        model.addAttribute("title", "Новый аск");
        log.error(ex.getMessage());
        return "layout";
    }

    @ExceptionHandler(AskFormInvalidException.class)
    public String handleAskFormInvalidException(AskFormInvalidException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("contentPage", "/WEB-INF/views/create_ask.jsp");
        model.addAttribute("title", "Новый аск");
        log.error("AskFormInvalidException: {}", ex.getMessage());
        return "layout";
    }

    @ExceptionHandler(PersonageFormInvalidException.class)
    public String handlePersonageFormInvalidException(PersonageFormInvalidException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("contentPage", "/WEB-INF/views/create_personage.jsp");
        model.addAttribute("title", "Новый персонаж");
        log.error("PersonageFormInvalidException: {}", ex.getMessage());
        return "layout";
    }

    @ExceptionHandler(AnswerFormInvalidException.class)
    public String handleAnswerFormInvalidException(AnswerFormInvalidException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("contentPage", "/WEB-INF/views/create_answer.jsp");
        model.addAttribute("title", "Новый ответ");
        log.error("AnswerFormInvalidException: {}", ex.getMessage());
        return "layout";
    }

    @ExceptionHandler(Exception.class)
    public String handleOtherExceptions(Exception ex) {
        log.error(ex.getMessage());
        return "error_page";
    }
}
