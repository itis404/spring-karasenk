package semestrovka.askosite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import semestrovka.askosite.dto.PersonageDto;
import semestrovka.askosite.dto.QuestionDto;
import semestrovka.askosite.entity.Personage;
import semestrovka.askosite.entity.User;
import semestrovka.askosite.service.PersonageService;
import semestrovka.askosite.service.QuestionService;
import semestrovka.askosite.service.SecurityUserDetails;

@Controller
@RequiredArgsConstructor
@RequestMapping("/personages/{personageUniqueName}/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final PersonageService personageService;

    @GetMapping
    public String getQuestionForm(
            @PathVariable String personageUniqueName,
            Model model,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(defaultValue = "0") int page
            ) {
        User user = ((SecurityUserDetails)userDetails).getUser();
        PersonageDto personage = personageService.getByUniqueName(personageUniqueName, user);
        Slice<QuestionDto> questions = questionService.getByPersonage(personageUniqueName, page, 30);
        model.addAttribute("personage", personage);
        model.addAttribute("questions", questions);
        model.addAttribute("title", "Вопросы");
        model.addAttribute("personageContentPage", "/WEB-INF/views/question_list.jsp");
        model.addAttribute("contentPage", "/WEB-INF/views/personage.jsp");
        return "layout";
    }

    @PostMapping
    public String createQuestion(
            @PathVariable String personageUniqueName,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam String text
    ) {
        User user = ((SecurityUserDetails)userDetails).getUser();
        questionService.create(user, personageUniqueName, text);

        return "redirect:/personages/" + personageUniqueName + "/questions";
    }
}
