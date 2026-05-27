package semestrovka.askosite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import semestrovka.askosite.dto.AskCreateRequest;
import semestrovka.askosite.dto.AskDto;
import semestrovka.askosite.exceptions.AskFormInvalidException;
import semestrovka.askosite.service.AskService;
import semestrovka.askosite.service.SecurityUserDetails;

@Controller
@RequiredArgsConstructor
@RequestMapping("/asks")
public class AskController {
    private final AskService askService;

    @PostMapping("/create")
    public String createAsk(
            @Valid @ModelAttribute AskCreateRequest request,
            BindingResult result,
            Authentication authentication) {

        if (result.hasErrors()){
            throw new AskFormInvalidException(result.getFieldErrors());
        }
        askService.create(request, ((SecurityUserDetails)authentication.getPrincipal()).getUser());
        return "redirect:/asks/" + request.getUniqueName();
    }

    @GetMapping("/create")
    public String getAskForm(Model model){
        model.addAttribute("contentPage", "/WEB-INF/views/create_ask.jsp");
        model.addAttribute("title", "Новый аск");
        return "layout";
    }

    @GetMapping("/{unique_name}")
    public String getAsk(@PathVariable("unique_name") String uniqueName, @RequestParam(defaultValue = "0") int page, Model model){
        AskDto ask = askService.getByUniqueName(uniqueName, page, 20);
        model.addAttribute("ask", ask);
        model.addAttribute("contentPage", "/WEB-INF/views/ask.jsp");
        model.addAttribute("title", ask.name());
        return "layout";
    }
}
