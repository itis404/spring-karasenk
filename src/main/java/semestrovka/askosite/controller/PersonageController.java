package semestrovka.askosite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import semestrovka.askosite.dto.AnswerDto;
import semestrovka.askosite.dto.PersonageCreateRequest;
import semestrovka.askosite.dto.PersonageDto;
import semestrovka.askosite.entity.User;
import semestrovka.askosite.exceptions.PersonageFormInvalidException;
import semestrovka.askosite.service.AnswerService;
import semestrovka.askosite.service.PersonageService;
import semestrovka.askosite.service.SecurityUserDetails;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class PersonageController {

    private final PersonageService personageService;
    private final AnswerService answerService;

    @GetMapping("asks/{ask}/personages")
    public String getPersonages(Model model,
                                @PathVariable("ask") String askUniqueName,
                                @RequestParam(defaultValue = "0") int page
                                )
    {
        Slice<PersonageDto> personages = personageService.getAllByAsk(askUniqueName, page, 10);
        model.addAttribute("contentPage", "/WEB-INF/views/personage_list.jsp");
        model.addAttribute("title", "Персонажи");
        model.addAttribute("askUrl", askUniqueName);
        model.addAttribute("currentPage", page);
        model.addAttribute("personages", personages);
        return "layout";
    }

    @GetMapping("asks/{ask}/personages/create")
    public String getForm(Model model, @PathVariable("ask") String askUniqueName) {
        model.addAttribute("contentPage", "/WEB-INF/views/create_personage.jsp");
        model.addAttribute("title", "Новый персонаж");
        model.addAttribute("askUrl", askUniqueName);
        return "layout";
    }

    @PostMapping("asks/{ask}/personages/create")
    public String postForm(
            @PathVariable("ask") String askUniqueName,
            @Valid @ModelAttribute PersonageCreateRequest request,
            BindingResult result,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User user = ((SecurityUserDetails) userDetails).getUser();
        if (result.hasErrors()) {
            throw new PersonageFormInvalidException(result.getFieldErrors().stream().map(
                    DefaultMessageSourceResolvable::getDefaultMessage
            ).toList());
        }

        personageService.create(askUniqueName, request, user);

        return "redirect:/personages/" + request.getUniqueName();
    }

    @GetMapping("personages/{personage}")
    public String getPersonage(
            @RequestParam(defaultValue = "0") int page,
            @PathVariable("personage") String personageUniqueName,
            @AuthenticationPrincipal UserDetails userDetails,
            Model model
            )
    {   User user = ((SecurityUserDetails) userDetails).getUser();
        PersonageDto personage = personageService.getByUniqueName(personageUniqueName, user);
        Slice<AnswerDto> answers = answerService.getByPersonage(page, 10, personageUniqueName);
        model.addAttribute("contentPage", "/WEB-INF/views/personage.jsp");
        model.addAttribute("personageContentPage", "/WEB-INF/views/answer_list.jsp");
        model.addAttribute("title", personageUniqueName);
        model.addAttribute("personage", personage);
        model.addAttribute("answers", answers);
        return "layout";
    }

    @PostMapping("personages/{personage}/delete")
    public String delete(
            @PathVariable("personage") String personageUniqueName,
            @AuthenticationPrincipal UserDetails userDetails
    ){
        User user = ((SecurityUserDetails) userDetails).getUser();
        personageService.checkPersonageOwner(personageUniqueName, user);
        personageService.delete(personageUniqueName);
        return "redirect:/home";
    }

    @GetMapping("personages/{personage}/edit")
    public String edit(
            @PathVariable("personage") String personageUniqueName,
            @AuthenticationPrincipal UserDetails userDetails,
            Model model
    ){
        User user = ((SecurityUserDetails) userDetails).getUser();
        personageService.checkPersonageOwner(personageUniqueName, user);
        PersonageDto personage = personageService.getByUniqueName(personageUniqueName, user);
        model.addAttribute("personage", personage);
        model.addAttribute("contentPage", "/WEB-INF/views/edit_personage.jsp");
        model.addAttribute("title", "Редактировать персонажа");

        return "layout";
    }

    @PostMapping("personages/{personage}/edit")
    public String editPost(
            @PathVariable("personage") String personageUniqueName,
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @ModelAttribute PersonageCreateRequest request
    ){
        User user = ((SecurityUserDetails) userDetails).getUser();
        personageService.checkPersonageOwner(personageUniqueName, user);
        personageService.edit(request);
        return "redirect:/personages/" + personageUniqueName;
    }
}