package semestrovka.askosite.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import semestrovka.askosite.validation.Image;
import semestrovka.askosite.validation.ShortName;

@Data
public class AskCreateRequest {
    @NotBlank(message = "Придумайте уникальное имя")
    @ShortName
    private String uniqueName;

    @NotBlank(message = "Придумайте название")
    private String name;

    @Image
    private MultipartFile icon;
}
