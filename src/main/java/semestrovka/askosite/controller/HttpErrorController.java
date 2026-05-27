package semestrovka.askosite.controller;

import org.springframework.stereotype.Controller;

@Controller
public class ErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        model.addAttribute("status", status);
        model.addAttribute("message", message);

        return "error"; // /WEB-INF/views/error.jsp
    }
}