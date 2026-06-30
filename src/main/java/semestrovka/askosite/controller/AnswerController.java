package semestrovka.askosite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import semestrovka.askosite.dto.AnswerCreateRequest;
import semestrovka.askosite.entity.User;
import semestrovka.askosite.exceptions.AnswerFormInvalidException;
import semestrovka.askosite.service.AnswerService;
import semestrovka.askosite.service.AskService;
import semestrovka.askosite.service.PersonageService;
import semestrovka.askosite.service.SecurityUserDetails;

import java.util.UUID;


@Controller
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final AskService askService;
    private final PersonageService personageService;

    @GetMapping("/personages/{personage}/new-answer")
    public String getForm(Model model,
                          @PathVariable("personage") String personageUniqueName,
                          @AuthenticationPrincipal UserDetails userDetails
                          ){
        User user = ((SecurityUserDetails) userDetails).getUser();
        personageService.checkPersonageOwner(personageUniqueName, user);
        model.addAttribute("personageUniqueName", personageUniqueName);
        model.addAttribute("contentPage", "/WEB-INF/views/create_answer.jsp");
        model.addAttribute("title", "Новый ответ");
        return "layout";
    }

    @PostMapping("/personages/{personage}/new-answer")
    public String postForm(@PathVariable("personage") String personageUniqueName,
                           @Valid @ModelAttribute AnswerCreateRequest request,
                           BindingResult result,
                           @AuthenticationPrincipal UserDetails userDetails
                           )
    {
        if (result.hasErrors()){
            throw new AnswerFormInvalidException(result.getFieldErrors().stream().map(
                    DefaultMessageSourceResolvable::getDefaultMessage
            ).toList());
        }
        User user = ((SecurityUserDetails) userDetails).getUser();
        personageService.checkPersonageOwner(personageUniqueName, user);
        answerService.create(request, personageUniqueName);
        return "redirect:/personages/" + personageUniqueName;
    }

    @PostMapping("/asks/{ask}/answers/{answerId}/delete")
    public String delete(@AuthenticationPrincipal UserDetails userDetails,
                         @PathVariable("ask") String askUniqueName,
                         @PathVariable("answerId") UUID answerId
                         ){
        User user = ((SecurityUserDetails) userDetails).getUser();
        askService.checkAdmin(user, askUniqueName);
        answerService.delete(answerId);
        return "redirect:/asks/" + askUniqueName;
    }
}
