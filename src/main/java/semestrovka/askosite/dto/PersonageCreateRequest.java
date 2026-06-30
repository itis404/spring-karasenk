package semestrovka.askosite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PersonageCreateRequest {
    @NotBlank
    @Size(max = 64)
    private String name;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9_]{3,64}$")
    private String uniqueName;

    @Size(max = 4096)
    private String info;

    private MultipartFile icon;
}
