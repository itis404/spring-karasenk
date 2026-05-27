package semestrovka.askosite.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import semestrovka.askosite.exceptions.AskFormInvalidException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class FileStorageService {
    private static final Path UPLOAD_DIR = Paths.get("img");

    public String saveImage(MultipartFile file) {
        String filename = UUID.randomUUID().toString();

        try {
            Files.createDirectories(UPLOAD_DIR);
            file.transferTo(UPLOAD_DIR.resolve(filename));
        } catch (IOException e) {
            throw new AskFormInvalidException(List.of());
        }
        return filename;
    }
}
