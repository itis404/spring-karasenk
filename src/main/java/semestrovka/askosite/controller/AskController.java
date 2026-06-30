package semestrovka.askosite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import semestrovka.askosite.dto.AnswerDto;
import semestrovka.askosite.dto.AskCreateRequest;
import semestrovka.askosite.dto.AskDto;
import semestrovka.askosite.entity.User;
import semestrovka.askosite.exceptions.AskFormInvalidException;
import semestrovka.askosite.service.AnswerService;
import semestrovka.askosite.service.AskService;
import semestrovka.askosite.service.SecurityUserDetails;

@Controller
@RequiredArgsConstructor
@RequestMapping("/asks")
public class AskController {
    private final AskService askService;
    private final AnswerService answerService;

    @GetMapping
    public String getAskList(Model model, @RequestParam(defaultValue = "0") int page){
        Slice<AskDto> asks = askService.getAll(page, 30);
        model.addAttribute("currentPage", page);
        model.addAttribute("asks", asks);
        model.addAttribute("contentPage", "/WEB-INF/views/ask_list.jsp");
        model.addAttribute("title", "Аски");
        return "layout";
    }

    @PostMapping("/create")
    public String createAsk(
            @Valid @ModelAttribute AskCreateRequest request,
            BindingResult result,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (result.hasErrors()){
            throw new AskFormInvalidException(result.getFieldErrors().stream().map(
                    DefaultMessageSourceResolvable::getDefaultMessage
            ).toList()
            );
        }
        User user = ((SecurityUserDetails) userDetails).getUser();
        askService.create(request, user);
        return "redirect:/asks/" + request.getUniqueName();
    }

    @GetMapping("/create")
    public String getAskForm(Model model){
        model.addAttribute("contentPage", "/WEB-INF/views/create_ask.jsp");
        model.addAttribute("title", "Новый аск");
        return "layout";
    }

    @GetMapping("/{unique_name}")
    public String getAsk(
            @PathVariable("unique_name") String uniqueName,
            @RequestParam(defaultValue = "0") int page,
            Model model,
            @AuthenticationPrincipal UserDetails userDetails
            ){
        User user = ((SecurityUserDetails) userDetails).getUser();
        AskDto ask = askService.getByUniqueName(uniqueName, user);
        Slice<AnswerDto> answers = answerService.getByAsk(page, 20, uniqueName);
        model.addAttribute("ask", ask);
        model.addAttribute("askUrl", ask.getUniqueName());
        model.addAttribute("answers", answers);
        model.addAttribute("contentPage", "/WEB-INF/views/ask.jsp");
        model.addAttribute("title", ask.getName());
        return "layout";
    }
}
