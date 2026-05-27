package semestrovka.askosite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import semestrovka.askosite.dto.RegisterRequest;
import semestrovka.askosite.exceptions.RegisterInvalidException;
import semestrovka.askosite.service.AuthService;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute RegisterRequest request, BindingResult result) {
        if (result.hasErrors()){
            throw new RegisterInvalidException(result.getFieldErrors());
        }
        authService.register(request);

        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerGet(){
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}