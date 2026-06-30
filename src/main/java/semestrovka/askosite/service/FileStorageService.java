package semestrovka.askosite.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import semestrovka.askosite.exceptions.AnswerFormInvalidException;
import semestrovka.askosite.exceptions.AskFormInvalidException;
import semestrovka.askosite.exceptions.PersonageFormInvalidException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class FileStorageService {
    private static final Path UPLOAD_DIR = Paths.get("uploads");

    // надо будет удаление картинок тоже сделать, чтобы лишние не хранить (после апдейта перса н-р)

    public String saveImage(MultipartFile file, FormType formType) {
        String filename = UUID.randomUUID().toString();
        String originalName = file.getOriginalFilename();

        if (originalName == null || originalName.split("\\.").length != 2){
            throwError(formType);
        }
        filename += "." + originalName.split("\\.")[1];
        try {
            Files.createDirectories(UPLOAD_DIR);
            file.transferTo(UPLOAD_DIR.resolve(filename));
        } catch (IOException e) {
            throwError(formType);
        }
        return filename;
    }
    private void throwError(FormType formType){
        switch (formType){
            case ASK: throw new AskFormInvalidException(List.of("Ошибка чтения файла"));
            case PERSONAGE: throw new PersonageFormInvalidException(List.of("Ошибка чтения файла"));
            case ANSWER: throw new AnswerFormInvalidException(List.of("Ошибка чтения файла"));
        }
    }
}
