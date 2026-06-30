package semestrovka.askosite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class AnswerCreateRequest {
    @NotBlank
    private String text;
    @NotEmpty
    List<MultipartFile> images;
}
