package semestrovka.askosite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import semestrovka.askosite.dto.AnswerDto;
import semestrovka.askosite.service.AnswerService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/home")
public class HomeController {

    private final AnswerService answerService;

    @GetMapping
    public String home(Model model, @RequestParam(defaultValue = "0") int page) {
        Slice<AnswerDto> answers = answerService.getAll(page, 20);
        model.addAttribute("answers", answers);
        model.addAttribute("currentPage", page);
        model.addAttribute("contentPage", "/WEB-INF/views/answer_list.jsp");
        model.addAttribute("title", "Главная");
        return "layout";
    }
}
